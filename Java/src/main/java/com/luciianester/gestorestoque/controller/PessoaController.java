package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.GenericController;
import com.luciianester.gestorestoque.model.Pessoa;
import com.luciianester.gestorestoque.pessoa.PessoaResource;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends GenericController<Pessoa> {

	public PessoaController() {
		super("pessoa", new PessoaResource());
	}

	@Override
	public boolean ehNovo(Pessoa objeto) {
		if (objeto.getPessoaId()==null || objeto.getPessoaId().equals(0)) {
			return true;
		} else {
			return false;
		}
	}
	
}
