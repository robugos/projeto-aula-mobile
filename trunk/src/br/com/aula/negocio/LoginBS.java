package br.com.aula.negocio;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;

public class LoginBS {
	
	public String conectaLogin(String login, String senha) {

		String urlPost = "http://150.161.16.233:8080/Aulaweb/login.jsp"; // implementarcom url em jsp
		/*String urlGet = "http://150.161.16.233:8080/Aulaweb/login.jsp?nome="
				+ login
				+ "&senha="
				+ senha;*/
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
			
			resposta = respostaConvertida;

		} catch (Exception ex) {
			Log.i("erro", " " + ex);
		}
		return resposta;

	}

}