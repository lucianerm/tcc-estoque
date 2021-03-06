package com.luciianester.gestorestoque.enums;

public enum Tela {

	PESSOA("Pessoa", "pessoa"),
	SAIDA("Sa�da", "saida"),
	ENTRADA("Entrada", "entrada"),
	PRODUTO("Produto", "produto"),
	MARCA("Marca", "marca"),
	CATEGORIA("Categoria", "categoria"),
	PERFIL("Perfil", "perfil"),
	USUARIO("Usu�rio", "usuario"),
	SALDOS("Saldos", "saldos"),
	SALDOSVALOR("Hist�rico Entradas", "saldovalor");
	
	private String nome;
	private String caminho;
	
	private Tela(String nome, String caminho) {
		this.nome = nome;
		this.caminho = caminho;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getCaminho() {
		return caminho;
	}
	
}
