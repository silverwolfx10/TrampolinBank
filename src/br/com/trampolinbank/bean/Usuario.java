package br.com.trampolinbank.bean;

import java.sql.Date;

public class Usuario {
	private Integer id;
	private String 	nome_completo;
	private Integer	idade ;
	private Date	created_at;
	private Date 	updated_at;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nome_completo;
	}
	public void setNomeCompleto(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
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
