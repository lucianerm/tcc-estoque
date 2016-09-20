package com.luciianester.gestorestoque.recursos.privilegio;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Privilegio;

public class PrivilegioRecurso extends RecursoGenerico<Privilegio> {

	public PrivilegioRecurso(DAO dao) {
		super(Privilegio.class, dao);
	}

	@Override
	public Long getId(Privilegio objeto) {
		return objeto.getPrivilegioId();
	}

	@Override
	public boolean validacaoGravar(Privilegio objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Privilegio objeto) {
		return true;
	}

	@Override
	public boolean validacaoExcluir(Privilegio objeto) {
		return true;
	}

}
