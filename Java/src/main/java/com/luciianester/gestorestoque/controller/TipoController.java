package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ModelUtils;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Tipo;

@Controller
@RequestMapping("/tipo")
public class TipoController extends ControllerGenerico<Tipo> {

	public TipoController() {
		super("tipo", new ResourceGenerico<Tipo>(Tipo.class));
		 
	}

	@Override
	public Long getId(Tipo objeto) {
		return objeto.getTipoId();
	}

	@Override
	public boolean validacaoGravar(Tipo objeto, Model model) {
		ModelUtils modelUtils = new ModelUtils(model);
		if (objeto.getTipo() == null || objeto.getTipo().equals("")){
				modelUtils.setMensagemErro("O campo tipo é obrigatório.");
				return false;
		}else if (objeto.getTipo().length() < 2 || objeto.getTipo().length() > 3) {
			modelUtils.setMensagemErro("O campo tipo deve ser maior que dois e menor que tres caracteres.");
				return false;
		}else if(objeto.getQuantidade() == null || objeto.getQuantidade() == ""){
			
		}
		return true;
	}

	@Override
	public boolean validacaoAlterar(Tipo objeto, Model model) {
		return validacaoGravar(objeto, model);
	}

	@Override
	public boolean validacaoExcluir(Long id, Model model) {

		return false;
	}

}
