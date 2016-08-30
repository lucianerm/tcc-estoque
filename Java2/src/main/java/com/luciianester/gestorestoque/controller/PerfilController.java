package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.resources.perfil.PerfilResources;

@Controller
@RequestMapping("/perfil")
public class PerfilController extends ControllerGenerico<Perfil>{

	public PerfilController() {
		super("perfil");
	}
	
	@Override
	public void cadastrar(ResourceGenerico<Perfil> resource) throws Exception {
		super.cadastrar(resource);

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		this.addAttribute("tipos", tipos);
		
	}
	
	@Override
	public void editar(ResourceGenerico<Perfil> resource, Long id) throws Exception {
		super.editar(resource, id);

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		this.addAttribute("tipos", tipos);
		
	}

	@Override
	public ResourceGenerico<Perfil> newResource() {
		return new PerfilResources(new DAO());
	}
	
}
