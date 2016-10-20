package com.luciianester.gestorestoque.recursos.usuario;

import java.util.List;

import org.hibernate.criterion.Restrictions;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarTodos(String campoPesquisa) throws Exception {
		
		if (campoPesquisa==null || campoPesquisa.equals("")) {
			return super.listarTodos();
		} else {
			
			List<Usuario> lista = (List<Usuario>) this.getDao()
					.createCriteria(Usuario.class)
					.add(Restrictions.ilike("nome", "%"+campoPesquisa+"%"))
					.list();
			
			return lista;
			
		}
		
	}
	
}
