package br.com.aula.negocio;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;

public class CadastroBS {

	public String cadastrar (String email, String nome, String senha) {
		String urlPost ="http://150.161.16.233:8080/Aulaweb/cadastroUsuario.jsp"; //implementar com url em jsp

	 //implementar com url em jsp
		ArrayList <NameValuePair> parametrosPost = new ArrayList <NameValuePair>();
		parametrosPost.add(new BasicNameValuePair("email", email));
		parametrosPost.add(new BasicNameValuePair("nome", nome));
		parametrosPost.add(new BasicNameValuePair("senha", senha));
		String resposta = null;
		Log.i("logar : ", "vai entrar no try");

		try {
			resposta = ConexaoHttpClient.executaHttpPost(urlPost, parametrosPost);
			resposta = resposta.toString();
			resposta = resposta.replaceAll("\\s+","");
			Log.i("resposta: ", resposta);
			
		}catch (Exception ex) {
			Log.i("erro"," "+ex);
		}
		
		return resposta;
	}

}