package com.luciianester.gestorestoque.recursos.saida.item;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.SaidaItem;

public class SaidaItemRecurso extends RecursoGenerico<SaidaItem> {

	public SaidaItemRecurso(DAO dao) {
		super(SaidaItem.class, dao);
	}

	@Override
	public Long getId(SaidaItem objeto) {
		return objeto.getSaidaItemId();
	}
	
	@Override
	public boolean validacaoGravar(SaidaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(SaidaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoExcluir(SaidaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

}
