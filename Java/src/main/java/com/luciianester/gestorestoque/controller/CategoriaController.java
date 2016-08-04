package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ModelUtils;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Categoria;

@Controller
@RequestMapping("/categoria")
public class CategoriaController extends ControllerGenerico<Categoria> {

	public CategoriaController() {
		super("categoria", new ResourceGenerico<Categoria>(Categoria.class));
	}

	@Override
	public Long getId(Categoria objeto) {
		return objeto.getCategoriaId();
	}

	@Override
	public boolean validacaoGravar(Categoria objeto, Model model) {
		ModelUtils modelUtils = new ModelUtils(model);
			if(objeto.getDescricao() == null || objeto.getDescricao().equals("")){
				modelUtils.setMensagemErro("Campo obrigatório");
				return false;
			}
		return true;
	}

	@Override
	public boolean validacaoAlterar(Categoria objeto, Model model) {

		return validacaoGravar(objeto, model);
	}

	@Override
	public boolean validacaoExcluir(Long Id, Model model) {

		return false;
	}

}
