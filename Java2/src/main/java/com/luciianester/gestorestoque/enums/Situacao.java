package com.luciianester.gestorestoque.enums;

public enum Situacao {

	INATIVO("Inativo"),
	ATIVO("Ativo");
	
	private String nome;
	
	private Situacao(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
