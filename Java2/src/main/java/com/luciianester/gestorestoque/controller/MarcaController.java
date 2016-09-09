package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Marca;
import com.luciianester.gestorestoque.resources.marca.MarcaResources;

@Controller
@RequestMapping("/marca")
public class MarcaController extends ControllerGenerico<Marca>{

	public MarcaController() {
		super("marca");
	}

	@Override
	public ResourceGenerico<Marca> newResource() {
		return new MarcaResources(new DAO());
	}

}
