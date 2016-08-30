package com.luciianester.gestorestoque.resources.entrada;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Entrada;

public class EntradaResources extends ResourceGenerico<Entrada> {

	public EntradaResources() {
		super(Entrada.class);
	}

	@Override
	public Long getId(Entrada objeto) {
		
		return objeto.getEntradaId();
	}

	@Override
	public boolean validacaoGravar(Entrada objeto) {
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Entrada objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Entrada objeto) {
		return true;
	}

}
