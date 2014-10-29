package br.com.trampolinbank.bean;

import java.sql.Date;

public class Agenda {
	private Integer id;
	private Float	valor;
	private Conta	conta;
	private String 	descricao;
	private Date 	agendado = new Date(new java.util.Date().getTime());
	private Date	created_at;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getAgendado() {
		return agendado;
	}
	public void setAgendado(Date agendado) {
		this.agendado = agendado;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
	
}
