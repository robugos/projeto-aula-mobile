package br.com.aula;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import br.com.aula.dao.ConexaoHttpClient;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends Activity {
	
	EditText nomeEt, senhaEt, confirmaSenhaEt, emailEt;
	Button cadastrarBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		nomeEt = (EditText) findViewById(R.id.nomeet);
		senhaEt = (EditText) findViewById(R.id.senhaUsuarioet);
		confirmaSenhaEt = (EditText) findViewById(R.id.senhaConfirmaet);
		emailEt = (EditText) findViewById(R.id.emailet);
		cadastrarBt = (Button) findViewById(R.id.cadastrarbt);
		
		cadastrarBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				String urlPost ="http://150.161.16.233:8080/Aulaweb/login.jsp"; //implementar com url em jsp
				String urlGet = "http://150.161.16.233:8080/Aulaweb/login.jsp?email="+nomeEt.getText().toString()
						+"&senha="+senhaEt.getText().toString();
			 //implementar com url em jsp
				ArrayList <NameValuePair> parametrosPost = new ArrayList <NameValuePair>();
				parametrosPost.add(new BasicNameValuePair("nome", nomeEt.getText().toString()));
				parametrosPost.add(new BasicNameValuePair("senha", senhaEt.getText().toString()));
				String resposta = null;
				Log.i("logar : ", "vai entrar no try");

				try {
					resposta = ConexaoHttpClient.executaHttpPost(urlPost, parametrosPost);
					String respostaConvertida = resposta.toString();
					respostaConvertida = respostaConvertida.replaceAll("\\s+","");
					Log.i("resposta: ", respostaConvertida);
					
					mensagemExibir("Login", respostaConvertida);

					if (respostaConvertida.equals("1")) {
						mensagemExibir("Login", "Usuário válido");

					}else 
						mensagemExibir("Login", "Usuário inválido");
					
				}catch (Exception ex) {
					Log.i("erro"," "+ex);
					Toast.makeText(CadastroActivity.this, "Erro :"+ex,Toast.LENGTH_LONG);
				}
				
			}
		});
 	} 
	
	public void mensagemExibir (String titulo, String texto) {
		
		AlertDialog.Builder mensagem = new AlertDialog.Builder(CadastroActivity.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
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
