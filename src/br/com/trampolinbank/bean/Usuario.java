package br.com.trampolinbank.bean;

import java.sql.Date;

public class Usuario {
	private Integer id;
	private String 	nome_completo;
	private Integer	idade;
	private String 	email;
	private Integer	status;
	private Date	created_at;
	private Date 	updated_at;
	
	public Integer getId() {
		return id;
	}
	public Usuario setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getNomeCompleto() {
		return nome_completo;
	}
	public Usuario setNomeCompleto(String nome_completo) {
		this.nome_completo = nome_completo;
		return this;
	}
	public Integer getIdade() {
		return idade;
	}
	public Usuario setIdade(Integer idade) {
		this.idade = idade;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	public Usuario setEmail(String email) {
		this.email = email;
		return this;
	}
	public Integer getStatus() {
		return status;
	}
	public Usuario setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public Usuario setCreatedAt(Date created_at) {
		this.created_at = created_at;
		return this;
	}
	public Date getUpdatedAt() {
		return updated_at;
	}
	public Usuario setUpdatedAt(Date updated_at) {
		this.updated_at = updated_at;
		return this;
	}
	
}
