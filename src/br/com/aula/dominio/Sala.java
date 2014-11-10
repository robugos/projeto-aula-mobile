package br.com.aula.dominio;


public class Sala {
	
	public Sala(int id, String numero, String andar, Predio pedrio, boolean b) {
		// TODO Auto-generated constructor stub
	}
	private String id;
	private String numero;
	private String tipo;
	private String andar;
	private String obsevacao;
	private Predio predio;
	private boolean selected;
	
	public Sala(String id, String numero, String tipo, String andar, Predio predio, String observacao, boolean selected) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.andar = andar;
		this.predio = predio;
		this.obsevacao = observacao;
		this.selected = selected;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAndar() {
		return andar;
	}
	public void setAndar(String andar) {
		this.andar = andar;
	}
	public String getObsevacao() {
		return obsevacao;
	}
	public void setObsevacao(String obsevacao) {
		this.obsevacao = obsevacao;
	}
	public Predio getPredio() {
		return predio;
	}
	public void setPredio(Predio predio) {
		this.predio = predio;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

}
