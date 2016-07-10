package com.luciianester.gestorestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="pessoa_pk", sequenceName="pessoa_pk", initialValue=1)
public class Pessoa {

	@GeneratedValue(strategy=GenerationType.AUTO, generator="pessoa_pk")
	@Id
	private Long pessoaId;
	
	private String nome;

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
