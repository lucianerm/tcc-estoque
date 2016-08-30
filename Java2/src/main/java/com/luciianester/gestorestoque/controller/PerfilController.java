package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.resources.perfil.PerfilResources;

@Controller
@RequestMapping("/perfil")
public class PerfilController extends ControllerGenerico<Perfil>{

	public PerfilController() {
		super("perfil", new PerfilResources());
	}
	
	@Override
	public void cadastrar() throws Exception {
		super.cadastrar();

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		this.addAttribute("tipos", tipos);
		
	}
	
	@Override
	public void editar(Long id) throws Exception {
		super.editar(id);

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		this.addAttribute("tipos", tipos);
		
	}
	
}
