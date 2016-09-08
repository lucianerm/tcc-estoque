package com.luciianester.gestorestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="endereco_pk", sequenceName="endereco_pk", initialValue=1)
public class Endereco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="endereco_pk")
	private Long enderecoId;
	
	@Column(length = 200)
	private String cep;
	
	@Column(length = 200)
	private String estado;
	
	@Column(length = 200)
	private String municipio;
	
	@Column(length = 200)
	private String bairro;
	
	@Column(length = 200)
	private String rua;
	
	@Column(length = 10)
	private String numero;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pessoaId")
	private Pessoa pessoa;

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
