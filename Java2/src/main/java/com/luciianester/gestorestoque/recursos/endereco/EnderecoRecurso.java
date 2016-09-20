package com.luciianester.gestorestoque.recursos.endereco;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Endereco;

public class EnderecoRecurso extends RecursoGenerico<Endereco> {

	public EnderecoRecurso(DAO dao) {
		super(Endereco.class, dao);
	}

	@Override
	public Long getId(Endereco objeto) {
		return objeto.getEnderecoId();
	}

	@Override
	public boolean validacaoGravar(Endereco objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Endereco objeto) {
		return true;
	}

	@Override
	public boolean validacaoExcluir(Endereco objeto) {
		return true;
	}

}
