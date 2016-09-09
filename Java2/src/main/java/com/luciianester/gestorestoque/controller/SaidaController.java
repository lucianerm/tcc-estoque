package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Saida;
import com.luciianester.gestorestoque.resources.saida.SaidaResources;

@Controller
@RequestMapping("/saida")
public class SaidaController extends ControllerGenerico<Saida>{

	public SaidaController() {
		super("saida");
	}

	@Override
	public ResourceGenerico<Saida> newResource() {
		return new SaidaResources(new DAO());
	}

}
