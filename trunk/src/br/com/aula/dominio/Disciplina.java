package br.com.aula.dominio;

public class Disciplina {

	private String id;
	private String nome;
	private Curso curso;
	private String professor;
	private boolean selected = false;

	public Disciplina(String id, String nome, Curso curso, String professor, boolean selected) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.professor = professor;
		this.selected = selected;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getIntId() {
		return Integer.parseInt(id);
	}
	
	public void setIntId(int id) {
		this.id = String.valueOf(id);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
}
