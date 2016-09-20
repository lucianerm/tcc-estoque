package com.luciianester.gestorestoque.recursos.entrada;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;

public class EntradaRecurso extends RecursoGenerico<Entrada> {

	public EntradaRecurso(DAO dao) {
		super(Entrada.class, dao);
	}

	@Override
	public Long getId(Entrada objeto) {
		
		return objeto.getEntradaId();
	}

	@Override
	public boolean validacaoGravar(Entrada objeto) {
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Entrada objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Entrada objeto) {
		return true;
	}

}
