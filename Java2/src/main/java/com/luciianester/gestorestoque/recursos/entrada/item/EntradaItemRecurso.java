package com.luciianester.gestorestoque.recursos.entrada.item;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.EntradaItem;

public class EntradaItemRecurso extends RecursoGenerico<EntradaItem> {

	public EntradaItemRecurso(DAO dao) {
		super(EntradaItem.class, dao);
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
