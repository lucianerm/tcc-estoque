package com.luciianester.gestorestoque.recursos.usuario;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Usuario;

public class UsuarioRecurso extends RecursoGenerico<Usuario> {

	public UsuarioRecurso(DAO dao) {
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
