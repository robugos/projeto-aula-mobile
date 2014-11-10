package br.com.aula.negocio;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Curso;


public class DisciplinaBS {
	
	public String pegarDisciplinas(Curso curso) {

		String resposta = null;
		String idCurso = curso.getIdCurso();
		String urlGet = "http://150.161.16.233:8080/Aulaweb/listarDisciplinas.jsp?idCurso="+idCurso;
		
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
