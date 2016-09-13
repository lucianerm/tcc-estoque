package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.Situacao;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.model.Usuario;
import com.luciianester.gestorestoque.resources.perfil.PerfilResources;
import com.luciianester.gestorestoque.resources.usuario.UsuarioResources;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends ControllerGenerico<Usuario>{

	public UsuarioController() {
		super("usuario");
	}

	@Override
	public ResourceGenerico<Usuario> newResource() {
		return new UsuarioResources(new DAO());
	}
	
	@SuppressWarnings("resource")
	private void setListas(ResourceGenerico<Usuario> resource) throws Exception {
		
		List<Situacao> situacoes = new ArrayList<Situacao>(Arrays.asList(Situacao.values()));
		this.addAttribute("situacoes", situacoes);
		
		List<Perfil> listPerfil = (List<Perfil>) new PerfilResources(resource.getDao()).listarTodos();
		this.addAttribute("listPerfil", listPerfil);
		
	}

	
	@Override
	public void cadastrar(ResourceGenerico<Usuario> resource) throws Exception {
		super.cadastrar(resource);
		this.setListas(resource);
	}
	
	@Override
	public void editar(ResourceGenerico<Usuario> resource, Long id) throws Exception {
		super.editar(resource, id);
		this.setListas(resource);
	}
	
}
