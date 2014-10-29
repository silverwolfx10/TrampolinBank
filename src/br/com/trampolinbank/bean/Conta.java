package br.com.trampolinbank.bean;

import java.sql.Date;

public class Conta {
	protected Integer 	id;
	protected Usuario		usuario;
	protected String 		agencia;
	protected String 		conta;
	protected TipoConta	tipo_conta;
	protected String 		password;
	protected Float		saldo_poupanca;
	protected Float 		saldo_corrente;
	protected Integer		status;
	protected Date		created_at;
	protected Date 		updated_at;
	
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
	public TipoConta getTipoConta() {
		return tipo_conta;
	}
	public void setTipoConta(TipoConta tipo_conta) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
