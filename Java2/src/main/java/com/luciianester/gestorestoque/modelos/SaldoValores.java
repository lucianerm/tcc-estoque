package com.luciianester.gestorestoque.modelos;

import java.util.ArrayList;
import java.util.List;

public class SaldoValores {
	
	private Long produtoId;
	private List<SaldoValor> listaProdutos = new ArrayList<>();

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public List<SaldoValor> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<SaldoValor> listaSaldoValor) {
		this.listaProdutos = listaSaldoValor;
	}

}
