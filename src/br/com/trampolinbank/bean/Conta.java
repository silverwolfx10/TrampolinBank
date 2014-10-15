package br.com.trampolinbank.bean;

import java.sql.Date;

public class Conta {
	private Integer id;
	private Usuario usuario;
	private String 	agencia;
	private String 	conta;
	private Integer	tipo_conta;
	private String 	password;
	private Float	saldo_poupanca;
	private Float 	saldo_corrente;
	private Date	created_at;
	private Date 	updated_at;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public Integer getTipoConta() {
		return tipo_conta;
	}
	public void setTipoConta(Integer tipo_conta) {
		this.tipo_conta = tipo_conta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Float getSaldoPoupanca() {
		return saldo_poupanca;
	}
	public void setSaldoPoupanca(Float saldo_poupanca) {
		this.saldo_poupanca = saldo_poupanca;
	}
	public Float getSaldoCorrente() {
		return saldo_corrente;
	}
	public void setSaldoCorrente(Float saldo_corrente) {
		this.saldo_corrente = saldo_corrente;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
