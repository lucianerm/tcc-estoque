package com.luciianester.gestorestoque.resources.pessoa;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Pessoa;

public class PessoaResources extends ResourceGenerico<Pessoa>{

	public PessoaResources(DAO dao) {
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
