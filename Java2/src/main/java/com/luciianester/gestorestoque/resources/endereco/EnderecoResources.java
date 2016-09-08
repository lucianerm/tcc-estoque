package com.luciianester.gestorestoque.resources.endereco;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Endereco;

public class EnderecoResources extends ResourceGenerico<Endereco> {

	public EnderecoResources(DAO dao) {
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
