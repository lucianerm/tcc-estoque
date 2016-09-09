package com.luciianester.gestorestoque.resources.categoria;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Categoria;

public class CategoriaResources extends ResourceGenerico<Categoria> {

	public CategoriaResources(DAO dao) {
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
