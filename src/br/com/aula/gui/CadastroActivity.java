package br.com.aula.gui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.aula.R;
import br.com.aula.dao.ConexaoHttpClient;

public class CadastroActivity extends Activity {
	
	EditText nomeEt, senhaEt, confirmaSenhaEt, emailEt;
	Button cadastrarBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
        StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_cadastro);

		nomeEt = (EditText) findViewById(R.id.nomeet);
		senhaEt = (EditText) findViewById(R.id.senhaUsuarioet);
		confirmaSenhaEt = (EditText) findViewById(R.id.senhaConfirmaet);
		emailEt = (EditText) findViewById(R.id.emailet);
		cadastrarBt = (Button) findViewById(R.id.cadastrarbt);
		
		cadastrarBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				validacao();
				
			}
		});
 	} 
	
	private void validacao () {
		boolean confirmaEmail = validarEmail(emailEt.getText().toString());
		if (nomeEt.getText().toString().equals("") || senhaEt.getText().toString().equals("") || 
				confirmaSenhaEt.getText().toString().equals("") || emailEt.getText().toString().equals("")) {
					mensagemExibir("Atenção", "Preencha todos os campos");
			}else if (!senhaEt.getText().toString().equals(confirmaSenhaEt.getText().toString())) {
				mensagemExibir("Atenção", "As senhas informadas não coincidem"); }
			else if (confirmaEmail == false) {
				mensagemExibir("Atenção", "O email informado não é válido");

			} else {
				cadastrar();
			}
	}
	
	public void mensagemExibir (String titulo, String texto) {
		
		AlertDialog.Builder mensagem = new AlertDialog.Builder(CadastroActivity.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}
	
	public boolean validarEmail (String email) {
		boolean retorno = false;
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+" +
				"[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);
		
		if (m.find()) {
			retorno = true;
		}
		
		return retorno;
	}
	
	public void cadastrar () {
		String urlPost ="http://150.161.16.233:8080/Aulaweb/cadastroUsuario.jsp"; //implementar com url em jsp
		//String urlGet = "http://150.161.16.233:8080/Aulaweb/login.jsp?email="+nomeEt.getText().toString()
			//	+"&senha="+senhaEt.getText().toString();
	 //implementar com url em jsp
		ArrayList <NameValuePair> parametrosPost = new ArrayList <NameValuePair>();
		parametrosPost.add(new BasicNameValuePair("email", emailEt.getText().toString()));
		parametrosPost.add(new BasicNameValuePair("nome", nomeEt.getText().toString()));
		parametrosPost.add(new BasicNameValuePair("senha", senhaEt.getText().toString()));
		String resposta = null;
		Log.i("logar : ", "vai entrar no try");

		try {
			resposta = ConexaoHttpClient.executaHttpPost(urlPost, parametrosPost);
			String respostaConvertida = resposta.toString();
			respostaConvertida = respostaConvertida.replaceAll("\\s+","");
			Log.i("resposta: ", respostaConvertida);
			
			if (respostaConvertida.equals("1")) {
				//mensagemExibir("Sucesso", "Usuário cadastrado!");
				chamaLogin();

			}else 
				mensagemExibir("Erro", "Usuário não cadastrado");
			
		}catch (Exception ex) {
			Log.i("erro"," "+ex);
			Toast.makeText(CadastroActivity.this, "Erro :"+ex,Toast.LENGTH_LONG);
		}
	}
	
	public void chamaLogin() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setTitle("Cadastrado")
				.setMessage("Usuário cadastrado com sucesso")
				.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).show();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
}
