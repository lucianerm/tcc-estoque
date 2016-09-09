package com.luciianester.gestorestoque.resources.saida;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Saida;

public class SaidaResources extends ResourceGenerico<Saida> {

	public SaidaResources(DAO dao) {
		super(Saida.class, dao);
	}

	@Override
	public Long getId(Saida objeto) {
		
		return objeto.getSaidaId();
	}

	@Override
	public boolean validacaoGravar(Saida objeto) {
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Saida objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Saida objeto) {
		return true;
	}

}
