package br.com.aula.negocio;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;

public class FavoritoBS {

	public String favoritar(String tipo, String id) {
		String urlPost ="http://150.161.16.233:8080/Aulaweb/favoritar.jsp?fav=true"; //implementar com url em jsp

	 //implementar com url em jsp
		ArrayList <NameValuePair> parametrosPost = new ArrayList <NameValuePair>();
		parametrosPost.add(new BasicNameValuePair("tipo", tipo));
		parametrosPost.add(new BasicNameValuePair("id", id));
		String resposta = null;

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
	
	public String desfavoritar(String tipo, String id) {
		String urlPost ="http://150.161.16.233:8080/Aulaweb/favoritar.jsp?fav=false"; //implementar com url em jsp

	 //implementar com url em jsp
		ArrayList <NameValuePair> parametrosPost = new ArrayList <NameValuePair>();
		parametrosPost.add(new BasicNameValuePair("tipo", tipo));
		parametrosPost.add(new BasicNameValuePair("id", id));
		String resposta = null;

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