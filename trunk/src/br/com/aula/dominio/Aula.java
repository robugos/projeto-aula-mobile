package br.com.aula.dominio;

import java.sql.Date;

public class Aula {
	
	public Aula(int parseInt, String horarioInicio2, String horarioFim2,
			String diaDaSemana2, String disciplina2, String sala2, boolean b) {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public Date getHorarioFim() {
		return horarioFim;
	}
	public void setHorarioFim(Date horarioFim) {
		this.horarioFim = horarioFim;
	}
	public Date getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(Date diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	private int id;
	private Date horarioInicio;
	private Date horarioFim;
	private Date diaDaSemana;
	private Disciplina disciplina;
	private Sala sala;

}
