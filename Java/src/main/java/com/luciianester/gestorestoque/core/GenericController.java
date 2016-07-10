package com.luciianester.gestorestoque.core;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class GenericController<T> {

	private String caminho;
	private GenericResource<T> resource;

	public GenericResource<T> getRes() {
		return resource;
	}
	
	public GenericController(String caminho, GenericResource<T> resource) {
		this.caminho = caminho;
		this.resource = resource;
	}
	
	@RequestMapping("/pesquisa")
	public String pesquisar(Model model) {
		
		List<T> lista = this.getRes().listAll();
		model.addAttribute("lista", lista);
		
		return this.caminho+"/pesquisa";
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(Model model) throws Exception {
		
		T objeto = this.getRes().newInstance();
		model.addAttribute("objeto", objeto);
		
		return this.caminho+"/cadastro";
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@ModelAttribute("objeto") T objeto) throws Exception {
		
		if (this.ehNovo(objeto)) {
			this.getRes().salvar(objeto);
		} else {
			this.getRes().alterar(objeto);
		}
		
		return "redirect:/"+this.caminho+"/cadastro";
		
	}
	
	@RequestMapping("/{id}")
	public String editar(@PathVariable("id") Long id, Model model) throws Exception {
		
		T objeto = this.getRes().listById(id);
		model.addAttribute("objeto", objeto);
		
		return this.caminho+"/cadastro";
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id) throws Exception {
		
		this.getRes().deletar(id);
		
		return "redirect:/"+this.caminho+"/pesquisa";
		
	}
	
	public abstract boolean ehNovo(T objeto);
	
}
