package br.com.aula;

public class Curso {
	  
	 String codCurso = null;
	 String nome = null;
	 boolean selected = false;
	  
	 public Curso(String codCurso, String nome, boolean selected) {
	  super();
	  this.codCurso = codCurso;
	  this.nome = nome;
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
