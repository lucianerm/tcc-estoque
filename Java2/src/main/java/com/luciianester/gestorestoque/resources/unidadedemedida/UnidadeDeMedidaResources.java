package com.luciianester.gestorestoque.resources.unidadedemedida;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;

public class UnidadeDeMedidaResources extends ResourceGenerico<UnidadeDeMedida> {

	public UnidadeDeMedidaResources(DAO dao) {
		super(UnidadeDeMedida.class, dao);
	}

	@Override
	public Long getId(UnidadeDeMedida objeto) {
		return objeto.getUnidadeDeMedidaId();
	}
	

	@Override
	public boolean validacaoGravar(UnidadeDeMedida objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(UnidadeDeMedida objeto) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean validacaoExcluir(UnidadeDeMedida objeto) {
		// TODO Auto-generated method stub
		return true;
	}

}
