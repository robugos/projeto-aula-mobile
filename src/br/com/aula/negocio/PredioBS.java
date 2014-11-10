package br.com.aula.negocio;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;


public class PredioBS {
	
	private String urlGet = "http://150.161.16.233:8080/Aulaweb/listarPredios.jsp";
	
	public String pegarPredios() {

		String resposta = null;

		try {
			resposta = ConexaoHttpClient.executaHttpGet(urlGet);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("erro", " " + e);
		}
		return resposta;

	}
}
