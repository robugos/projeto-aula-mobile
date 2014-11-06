package br.com.aula.negocio;

import java.util.ArrayList;

import android.util.Log;
import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Curso;

public class CursoBS {
	
	
	public ArrayList<Curso> pegarCursos() throws Exception {
		 ArrayList<Curso> listCursos =null;

		String urlPost = "http://150.161.16.233:8080/Aulaweb/Cursos.jsp"; // implementarcom
		// url
		// em
		// jsp
		String urlGet = "http://150.161.16.233:8080/Aulaweb/Cursos" ;
		
		String resposta = null;

		
			resposta = ConexaoHttpClient.executaHttpGet("http://150.161.16.233:8080/Aulaweb/Cursos");
			
			

			// mensagemExibir("Login", respostaConvertida);

			
				String listaDosCursos = resposta;
				// mensagemExibir("Login", "Usuário válido");
				while(!listaDosCursos.equals(",")){
					int id = Integer.parseInt( listaDosCursos.substring(0,listaDosCursos.indexOf(",")-1));
					String nome = listaDosCursos.substring(listaDosCursos.indexOf(" "),listaDosCursos.indexOf(",")-1);
					String turno = listaDosCursos.substring(nome.length()+1 ,listaDosCursos.indexOf(",")-1);
					
					Curso curso = new Curso(id, nome, turno,true);
					listCursos.add(curso);		
					listaDosCursos = listaDosCursos.substring(turno.length()+1,-1);
					
				}
				return listCursos;
			
		

}
	
}
