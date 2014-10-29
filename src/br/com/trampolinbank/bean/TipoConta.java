package br.com.trampolinbank.bean;

import java.sql.Date;

public class TipoConta {
	private Integer id;
	private String 	descricao;

	public Integer getId() {
		return id;
	}
	public TipoConta setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getDescricao() {
		return descricao;
	}
	public TipoConta setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
}
