package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ModelUtils;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoController extends ControllerGenerico<Produto>{

	public ProdutoController() {
		super("produto", new ResourceGenerico<Produto>(Produto.class));
	}

	@Override
	public Long getId(Produto objeto) {
		
		return objeto.getProdutoId();
	}

	@Override
	public boolean validacaoGravar(Produto objeto, Model model) {
		ModelUtils modelUtils = new ModelUtils(model);
		if(objeto.getDescricao() == null || objeto.getDescricao().equals("") ){
			modelUtils.setMensagemErro("Campo descrição é obrigatório.");
			return false;
		}else if (objeto.getDescricao().length() <3){
				modelUtils.setMensagemErro("Campo descrição deve ser maior que três caracteres.");
				return false;
			}
					
		return true;
	}

	@Override
	public boolean validacaoAlterar(Produto objeto, Model model) {
		
		return validacaoGravar(objeto, model);
	}

	@Override
	public boolean validacaoExcluir(Long id, Model model) {
		return true;
	}

}
