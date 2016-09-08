package com.luciianester.gestorestoque.resources.telefone;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Telefone;

public class TelefoneResources extends ResourceGenerico<Telefone> {

	public TelefoneResources(DAO dao) {
		super(Telefone.class, dao);
	}

	@Override
	public Long getId(Telefone objeto) {
		return objeto.getTelefoneId();
	}

	@Override
	public boolean validacaoGravar(Telefone objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Telefone objeto) {
		return true;
	}

	@Override
	public boolean validacaoExcluir(Telefone objeto) {
		return true;
	}

}
