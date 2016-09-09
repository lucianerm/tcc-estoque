package com.luciianester.gestorestoque.resources.marca;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Marca;

public class MarcaResources extends ResourceGenerico<Marca> {

	public MarcaResources(DAO dao) {
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
