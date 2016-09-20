package com.luciianester.gestorestoque.recursos.pessoa;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Pessoa;

public class PessoaRecurso extends RecursoGenerico<Pessoa>{

	public PessoaRecurso(DAO dao) {
		super(Pessoa.class, dao);
	}

	@Override
	public Long getId(Pessoa objeto) {
		return objeto.getPessoaId();
	}

	@Override
	public boolean validacaoGravar(Pessoa objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Pessoa objeto) {
		return true;
	}

	@Override
	public boolean validacaoExcluir(Pessoa objeto) {
		return true;
	}

}
