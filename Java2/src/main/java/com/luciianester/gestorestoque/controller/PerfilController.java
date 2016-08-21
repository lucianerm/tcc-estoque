package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.model.Perfil;

@Controller
@RequestMapping("/perfil")
public class PerfilController extends ControllerGenerico<Perfil>{

	public PerfilController() {
		super("perfil", new ResourceGenerico<Perfil>(Perfil.class));
	}

	@Override
	public Long getId(Perfil objeto) {
		
		return objeto.getPerfilId();
	}

	@Override
	public boolean validacaoGravar(Perfil objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Perfil objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Long id) {
		return true;
	}
	
	
	@Override
	public void cadastrar() throws Exception {
		super.cadastrar();

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		for (PerfilTipo tipo : tipos) {
			System.out.println("aqui");
		}
		
		this.addAttribute("tipos", tipos);
		
	}
	
	@Override
	public void editar(Long id) throws Exception {
		super.editar(id);

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		for (PerfilTipo tipo : tipos) {
			System.out.println("aqui");
		}
		
		this.addAttribute("tipos", tipos);
		
	}
	
}
