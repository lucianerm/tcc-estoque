package com.luciianester.gestorestoque.resources.entrada.item;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.EntradaItem;

public class EntradaItemResources extends ResourceGenerico<EntradaItem> {

	public EntradaItemResources() {
		super(EntradaItem.class);
	}


	@Override
	public Long getId(EntradaItem objeto) {
		return objeto.getEntradaItemId();
	}
	
	@Override
	public boolean validacaoGravar(EntradaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(EntradaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoExcluir(EntradaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

}
