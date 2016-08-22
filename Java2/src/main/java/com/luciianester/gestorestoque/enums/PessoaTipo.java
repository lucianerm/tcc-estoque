package com.luciianester.gestorestoque.enums;

public enum PessoaTipo {

	FISICA("F�sica"),
	JURIDICA("Jur�dica");
	
	
	private String nome;
	
	private PessoaTipo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
