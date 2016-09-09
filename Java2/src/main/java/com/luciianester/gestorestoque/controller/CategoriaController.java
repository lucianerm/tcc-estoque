package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Categoria;
import com.luciianester.gestorestoque.resources.categoria.CategoriaResources;

@Controller
@RequestMapping("/categoria")
public class CategoriaController extends ControllerGenerico<Categoria>{

	public CategoriaController() {
		super("categoria");
	}

	@Override
	public ResourceGenerico<Categoria> newResource() {
		return new CategoriaResources(new DAO());
	}

}
