package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Entrada;

@Controller
@RequestMapping("/entrada")
public class EntradaController extends ControllerGenerico<Entrada>{

	public EntradaController() {
		super("entrada", new ResourceGenerico<Entrada>(Entrada.class));
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
	public boolean validacaoExcluir(Long id) {
		return true;
	}

}
