package br.com.trampolinbank.bean;

import java.sql.Date;

public class Operacao {
	private Integer id;
	private String 	descricao;
	private Float 	taxa;
	private Integer	prazo;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Float getTaxa() {
		return taxa;
	}
	public void setTaxa(Float taxa) {
		this.taxa = taxa;
	}
	public Integer getPrazo() {
		return prazo;
	}
	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	
	
	
}
