package br.com.aula.negocio;

import java.util.ArrayList;

import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Curso;

public class CadastroBS {

	public ArrayList<Curso> pegarCursos() throws Exception {
		ArrayList<Curso> listCursos = null;

		String urlGet = "http://150.161.16.234:8080/Aulaweb/listarCursos.jsp";

		String resposta = null;

		resposta = ConexaoHttpClient.executaHttpGet(urlGet);

		// mensagemExibir("Login", respostaConvertida);

		// mensagemExibir("Login", "Usuário válido");
		String[] parts = resposta.split(",");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split(" ");
			String idCurso = c[0];
			String nome = c[1];
			String turno = c[2];
			//Curso curso = new Curso(Integer.parseInt(idCurso), nome, turno,
			//		false);
			//listCursos.add(curso);
		}
		/*
		 * while(!listaDosCursos.equals(",")){ int id = Integer.parseInt(
		 * listaDosCursos.substring(0,listaDosCursos.indexOf(",")-1)); String
		 * nome =
		 * listaDosCursos.substring(listaDosCursos.indexOf(" "),listaDosCursos
		 * .indexOf(",")-1); String turno =
		 * listaDosCursos.substring(nome.length()+1
		 * ,listaDosCursos.indexOf(",")-1);
		 * 
		 * Curso curso = new Curso(id, nome, turno,true); listCursos.add(curso);
		 * listaDosCursos = listaDosCursos.substring(turno.length()+1,-1);
		 * 
		 * }
		 */
		return listCursos;

	}

}