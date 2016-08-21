package com.luciianester.gestorestoque.enums;

public enum PerfilTipo {

	ADMINISTRADOR("Administrador"),
	USUARIO("Usu�rio");
	
	
	private String nome;
	
	private PerfilTipo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
