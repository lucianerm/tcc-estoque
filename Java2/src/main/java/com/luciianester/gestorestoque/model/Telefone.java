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
@SequenceGenerator(name="telefone_pk", sequenceName="telefone_pk", initialValue=1)
public class Telefone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="telefone_pk")
	private Long telefoneId;
	
	@Column(length = 200)
	private String descricao;
	
	@Column
	private Integer numero;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pessoaId")
	private Pessoa pessoa;
	
	public Long getTelefoneId() {
		return telefoneId;
	}

	public void setTelefoneId(Long telefoneId) {
		this.telefoneId = telefoneId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
