package br.com.aula.negocio;

import java.util.ArrayList;

import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Aula;

public class AulaBS {

	public ArrayList<Aula> pegarAulas() throws Exception {
		ArrayList<Aula> listAulas = null;

		String urlGet = "http://150.161.16.234:8080/Aulaweb/listarAulas.jsp";

		String resposta = null;

		resposta = ConexaoHttpClient.executaHttpGet(urlGet);

		// mensagemExibir("Login", respostaConvertida);

		// mensagemExibir("Login", "Usuário válido");
		String[] parts = resposta.split(",");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split(" ");
			String id = c[0];
			String horarioInicio = c[1];
			String horarioFim = c[2];
			String diaDaSemana = c[3];
			String disciplina = c[4];
			String sala = c[5];
			Aula aula = new Aula(Integer.parseInt(id), horarioInicio, horarioFim, diaDaSemana, disciplina, sala, false);
			listAulas.add(aula);
		}
		return listAulas;

	}

}