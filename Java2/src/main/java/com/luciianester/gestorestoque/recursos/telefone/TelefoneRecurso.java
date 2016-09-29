package com.luciianester.gestorestoque.recursos.telefone;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.entidades.Telefone;

public class TelefoneRecurso extends RecursoGenerico<Telefone> {

	public TelefoneRecurso(DAO dao) {
		super(Telefone.class, dao);
	}

	@Override
	public Long getId(Telefone objeto) {
		return objeto.getTelefoneId();
	}

	@Override
	public boolean validacaoGravar(Telefone objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Telefone objeto) {
		return true;
	}

	@Override
	public boolean validacaoExcluir(Telefone objeto) {
		return true;
	}
	
	public List<Telefone> listarPelaPessoa(Pessoa pessoa) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Telefone> lista = (List<Telefone>) this.getDao().createCriteria(Telefone.class)
			.add(Restrictions.eq("pessoa", pessoa))
			.list();
		
		return lista;
		
	}

}
