package com.luciianester.gestorestoque.modelos;

import java.util.List;

public class Saldos {

	private String data;
	private List<Saldo> listaProdutos;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Saldo> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Saldo> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
}
