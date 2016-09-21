package com.luciianester.gestorestoque.recursos.entrada.item;

import java.util.ArrayList;
import java.util.List;

import com.luciianester.gestorestoque.entidades.EntradaItem;

public class EntradaItemDoProduto {

	private List<EntradaItem> lista = new ArrayList<>();

	public List<EntradaItem> getLista() {
		return lista;
	}

	public void setLista(List<EntradaItem> lista) {
		this.lista = lista;
	}
	
}
