package com.luciianester.gestorestoque.recursos.marca;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Marca;

public class MarcaRecurso extends RecursoGenerico<Marca> {

	public MarcaRecurso(DAO dao) {
		super(Marca.class, dao);
	}

	@Override
	public Long getId(Marca objeto) {
		
		return objeto.getMarcaId();
	}

	@Override
	public boolean validacaoGravar(Marca objeto) {
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Marca objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Marca objeto) {
		return true;
	}

}
