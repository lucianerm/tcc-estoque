package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;

@Controller
@RequestMapping("/produto")
public class ProdutoController extends ControllerGenerico<Produto>{

	public ProdutoController() {
		super("produto");
	}

	@Override
	public ResourceGenerico<Produto> newResource() {
		return new ProdutoResources(new DAO());
	}

}
