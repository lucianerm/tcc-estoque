package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ModelUtils;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Pessoa;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends ControllerGenerico<Pessoa> {

	public PessoaController() {
		super("pessoa", new ResourceGenerico<Pessoa>(Pessoa.class));
	}

	@Override
	public Long getId(Pessoa objeto) {
		return objeto.getPessoaId();
	}

	@Override
	public boolean validacaoGravar(Pessoa objeto, Model model) {
		
		ModelUtils modUtil = new ModelUtils(model);
		
		if (objeto.getNome()!=null) {
			
			objeto.setNome(objeto.getNome().trim());
			
			if (objeto.getNome().length()<3) {
				modUtil.setMensagemErro("Campo Nome é obrigatório.");
				return false;
			}
			
		} else {
			modUtil.setMensagemErro("Campo Nome é obrigatório.");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Pessoa objeto, Model model) {
		return this.validacaoGravar(objeto, model);
	}

	@Override
	public boolean validacaoExcluir(Long id, Model model) {
		return true;
	}
	
}
