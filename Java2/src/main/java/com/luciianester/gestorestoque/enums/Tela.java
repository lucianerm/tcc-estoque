package com.luciianester.gestorestoque.enums;

public enum Tela {

	PRODUTO("Produto"),
	ENTRADA("Entrada");
	
	private String nome;
	
	private Tela(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
