package br.com.aula.negocio;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;


public class CursoBS {
	
	private String urlGet = "http://150.161.16.233:8080/Aulaweb/listarCursos.jsp";
	
	public String pegarCursos() {

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
