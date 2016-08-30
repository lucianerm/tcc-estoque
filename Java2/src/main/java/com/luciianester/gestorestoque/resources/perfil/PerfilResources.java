package com.luciianester.gestorestoque.resources.perfil;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Perfil;

public class PerfilResources extends ResourceGenerico<Perfil> {

	public PerfilResources() {
		super(Perfil.class);
	}

	@Override
	public Long getId(Perfil objeto) {
		// TODO Auto-generated method stub
		return objeto.getPerfilId();
	}

	@Override
	public boolean validacaoGravar(Perfil objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(Perfil objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoExcluir(Perfil objeto) {
		// TODO Auto-generated method stub
		return true;
	}

}
