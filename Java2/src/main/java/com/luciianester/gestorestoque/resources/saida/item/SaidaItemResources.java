package com.luciianester.gestorestoque.resources.saida.item;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.SaidaItem;

public class SaidaItemResources extends ResourceGenerico<SaidaItem> {

	public SaidaItemResources(DAO dao) {
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
