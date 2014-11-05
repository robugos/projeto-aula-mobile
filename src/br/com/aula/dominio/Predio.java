package br.com.aula.dominio;



public class Predio {
	
	private int id;
	private String nome;
	private String quantAndar;
	private String observacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantAndar() {
		return quantAndar;
	}
	public void setQuantAndar(String quantAndar) {
		this.quantAndar = quantAndar;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

}
