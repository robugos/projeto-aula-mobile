package br.com.aula.dominio;

public class SalaFavorita {

	private int id;
	private Sala sala;
	private Usuario usuario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sala getSala() {
		return sala;
	}
	public void setPredio(Sala sala) {
		this.sala = sala;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
