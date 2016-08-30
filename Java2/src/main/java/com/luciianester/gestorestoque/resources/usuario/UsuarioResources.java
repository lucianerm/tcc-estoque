package com.luciianester.gestorestoque.resources.usuario;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Usuario;

public class UsuarioResources extends ResourceGenerico<Usuario> {

	public UsuarioResources(DAO dao) {
		super(Usuario.class, dao);
	}

	@Override
	public Long getId(Usuario objeto) {
		return objeto.getUsuarioId();
	}

	@Override
	public boolean validacaoGravar(Usuario objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(Usuario objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoExcluir(Usuario objeto) {
		// TODO Auto-generated method stub
		return true;
	}

}
