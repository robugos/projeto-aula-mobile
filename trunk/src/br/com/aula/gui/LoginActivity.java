package br.com.aula.gui;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.aula.R;
import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.negocio.LoginBS;

@SuppressLint("ShowToast")
public class LoginActivity extends Activity {

	EditText loginEt, senhaEt;
	Button entrarBt, cadastrarBt;
	public LoginBS loginBS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.activity_login);

		loginEt = (EditText) findViewById(R.id.loginet);
		senhaEt = (EditText) findViewById(R.id.senhaet);
		entrarBt = (Button) findViewById(R.id.loginbt);
		cadastrarBt = (Button) findViewById(R.id.cadastrobt);

		cadastrarBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,
						CadastroActivity.class));
			}
		});

		entrarBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				verificaCampos();

			}

			private void verificaCampos() {
				if (loginEt.getText().toString().equals("")
						&& senhaEt.getText().toString().equals("")) {
					mensagemExibir("Erro", "Campos Login e Senha vazios");
				} else if (loginEt.getText().toString().equals("")) {
					mensagemExibir("Erro", "Campo Login vazio");
				} else if (senhaEt.getText().toString().equals("")) {
					mensagemExibir("Erro", "Campo Senha vazio");
				}else{
					String login = loginEt.getText().toString();
					String senha = senhaEt.getText().toString();
					conectaLogin(login, senha);
				}

			}
		});
	}
	
	public void conectaLogin(String login, String senha) {

		String urlPost = "http://150.161.16.233:8080/Aulaweb/login.jsp"; // implementarcom url em jsp
		String urlGet = "http://150.161.16.233:8080/Aulaweb/login.jsp?nome="
				+ login
				+ "&senha="
				+ senha;
		// implementar com url em jsp
		ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
		parametrosPost.add(new BasicNameValuePair("nome", login));
		parametrosPost.add(new BasicNameValuePair("senha", senha));
		String resposta = null;
		Log.i("logar : ", "vai entrar no try");

		try {
			resposta = ConexaoHttpClient.executaHttpPost(urlPost,
					parametrosPost);
			String respostaConvertida = resposta.toString();
			respostaConvertida = respostaConvertida.replaceAll("\\s+",
					"");
			Log.i("resposta: ", respostaConvertida);

			// mensagemExibir("Login", respostaConvertida);

			if (respostaConvertida.equals("1")) {
				// mensagemExibir("Login", "Usu√°rio v√°lido");
				startActivity(new Intent(this,
						MainActivity.class));
				finish();

			} else
				mensagemExibir("Login", "Usu·rio inv·lido");

		} catch (Exception ex) {
			Log.i("erro", " " + ex);
			Toast.makeText(this, "Erro :" + ex,
					Toast.LENGTH_LONG);
		}

	}

	public void mensagemExibir(String titulo, String texto) {

		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				LoginActivity.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
