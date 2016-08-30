package com.luciianester.gestorestoque.resources.entrada;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Entrada;

public class EntradaResources extends ResourceGenerico<Entrada> {

	public EntradaResources(DAO dao) {
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
