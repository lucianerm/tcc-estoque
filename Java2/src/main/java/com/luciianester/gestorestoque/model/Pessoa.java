package com.luciianester.gestorestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.luciianester.gestorestoque.enums.PessoaTipo;


@Entity
@SequenceGenerator(name="pessoa_pk", sequenceName="pessoa_pk", initialValue=1)
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="pessoa_pk")
	private Long pessoaId;
	
	@Column(length = 400)
	private String nome;

	@Column(length = 400)
	private String nomeFantasia;
	
	@Enumerated(EnumType.ORDINAL)
	private PessoaTipo tipo;
	
	@Column(length = 20)
	private String cpfoucnpj;
	
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public PessoaTipo getTipo() {
		return tipo;
	}

	public void setTipo(PessoaTipo tipo) {
		this.tipo = tipo;
	}

	public String getCpfoucnpj() {
		return cpfoucnpj;
	}

	public void setCpfoucnpj(String cpfoucnpj) {
		this.cpfoucnpj = cpfoucnpj;
	}

}