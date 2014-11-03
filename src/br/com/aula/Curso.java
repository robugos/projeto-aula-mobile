package br.com.aula;

public class Curso {
	  
	 private String codCurso = null;
	 private String nome = null;
	 private String turno = null;
	 private boolean selected = false;
	  
	 public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Curso(String codCurso, String nome, String turno, boolean selected) {
	  super();
	  this.codCurso = codCurso;
	  this.nome = nome;
	  this.turno = turno;
	  this.selected = selected;
	 }
	  
	 public String getCodCurso() {
	  return codCurso;
	 }
	 public void setCodCurso(String codCurso) {
	  this.codCurso = codCurso;
	 }
	 public String getNome() {
	  return nome;
	 }
	 public void setNome(String nome) {
	  this.nome = nome;
	 }
	 
	 public boolean isSelected() {
	  return selected;
	 }
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
	  
	}
