package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.PessoaTipo;
import com.luciianester.gestorestoque.model.Pessoa;
import com.luciianester.gestorestoque.resources.pessoa.PessoaResources;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends ControllerGenerico<Pessoa> {

	public PessoaController() {
		super("pessoa");
	}

	@Override
	public ResourceGenerico<Pessoa> newResource() {
		return new PessoaResources(new DAO());
	}

	private void setTipos() {
		List<PessoaTipo> tipos = new ArrayList<PessoaTipo>(Arrays.asList(PessoaTipo.values()));
		this.addAttribute("tipos", tipos);
	}
	
	@Override
	public void cadastrar(ResourceGenerico<Pessoa> resource) throws Exception {
		super.cadastrar(resource);
		this.setTipos();
	}
	
	@Override
	public void editar(ResourceGenerico<Pessoa> resource, Long id) throws Exception {
		super.editar(resource, id);
		this.setTipos();
	}
	
}
