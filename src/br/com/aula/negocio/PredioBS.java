package br.com.aula.negocio;

import java.util.ArrayList;

import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Predio;

public class PredioBS {

	public ArrayList<Predio> pegarPredios() throws Exception {
		ArrayList<Predio> listPredios = null;

		String urlGet = "http://150.161.16.234:8080/Aulaweb/listarPredios.jsp";

		String resposta = null;

		resposta = ConexaoHttpClient.executaHttpGet(urlGet);

		// mensagemExibir("Login", respostaConvertida);

		// mensagemExibir("Login", "Usuário válido");
		String[] parts = resposta.split(",");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split(" ");
			String id = c[0];
			String nome = c[1];
			String quantAndar = c[2];
			Predio predio = new Predio(Integer.parseInt(id), nome, quantAndar, false);
			listPredios.add(predio);
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
		return listPredios;

	}

}