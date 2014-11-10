package br.com.aula.negocio;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Predio;


public class SalaBS {
	
	public String pegarSalas(Predio predio) {

		String resposta = null;
		String idPredio = predio.getId();
		String urlGet = "http://150.161.16.233:8080/Aulaweb/listarSalas.jsp?idCurso="+idPredio;
		
		Log.i("urlGet antes do try", urlGet);
		
		try {
			Log.i("urlGet no try", urlGet);
			resposta = ConexaoHttpClient.executaHttpGet(urlGet);
		} catch (Exception e) {
			Log.i("erro", "erro no catch");
			e.printStackTrace();
			Log.i("erro", " " + e);
		}
		return resposta;

	}
}
