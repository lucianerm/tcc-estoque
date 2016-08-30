package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Entrada;
import com.luciianester.gestorestoque.resources.entrada.EntradaResources;

@Controller
@RequestMapping("/entrada")
public class EntradaController extends ControllerGenerico<Entrada>{

	public EntradaController() {
		super("entrada");
	}

	@Override
	public ResourceGenerico<Entrada> newResource() {
		return new EntradaResources(new DAO());
	}

}
