package com.luciianester.gestorestoque.recursos.categoria;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Categoria;

public class CategoriaRecurso extends RecursoGenerico<Categoria> {

	public CategoriaRecurso(DAO dao) {
		super(Categoria.class, dao);
	}

	@Override
	public Long getId(Categoria objeto) {
		
		return objeto.getCategoriaId();
	}

	@Override
	public boolean validacaoGravar(Categoria objeto) {
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Categoria objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Categoria objeto) {
		return true;
	}

}
