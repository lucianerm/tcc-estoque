package com.luciianester.gestorestoque.recursos.saida;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Saida;

public class SaidaRecurso extends RecursoGenerico<Saida> {

	public SaidaRecurso(DAO dao) {
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
