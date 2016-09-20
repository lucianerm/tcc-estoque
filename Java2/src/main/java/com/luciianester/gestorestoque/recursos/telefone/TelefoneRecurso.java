package com.luciianester.gestorestoque.recursos.telefone;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Telefone;

public class TelefoneRecurso extends RecursoGenerico<Telefone> {

	public TelefoneRecurso(DAO dao) {
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
