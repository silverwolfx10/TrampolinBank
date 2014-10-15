package br.com.trampolinbank.bean;

import java.sql.Date;

public class Movimentacao {
	
	private Integer id;
	private Conta 	conta_id;
	private Integer	tipo_conta;
	private String 	descricao;
	private	Float 	valor;
	private Date 	created_at;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Conta getContaId() {
		return conta_id;
	}
	public void setContaId(Conta conta_id) {
		this.conta_id = conta_id;
	}
	public Integer getTipoConta() {
		return tipo_conta;
	}
	public void setTipoConta(Integer tipo_conta) {
		this.tipo_conta = tipo_conta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
	
}
