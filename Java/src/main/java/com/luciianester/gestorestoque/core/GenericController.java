package com.luciianester.gestorestoque.core;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String cadastro(@RequestParam(required=false) String tipo, Model model) throws Exception {
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();;
			}
		}
		
		T objeto = this.getRes().newInstance();
		model.addAttribute("objeto", objeto);
		
		return this.caminho+"/cadastro";
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@ModelAttribute("objeto") T objeto, Model model) throws Exception {
		
		try {
		
			Long id = this.getId(objeto);
			
			if (this.ehNovo(objeto)) {
				if (this.validacaoGravar(objeto, model)) {
					this.getRes().salvar(objeto);
				} else {
					return this.caminho+"/cadastro";
				}
			} else {
				if (this.validacaoAlterar(objeto, model)) {
					this.getRes().alterar(objeto);
				} else {
					return this.caminho+"/cadastro";
				}
			}
			
			return "redirect:/"+this.caminho+"/"+this.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
			
		} catch (Exception e) {
			
			model.addAttribute("tipo", MensagemTipo.ERRO);
			model.addAttribute("mensagem", e.getMessage());
			
			model.addAttribute("objeto", objeto);
			
			return this.caminho+"/cadastro";
			
		}
			
	}
	
	@RequestMapping("/{id}")
	public String editar(@RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model model) throws Exception {
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();;
			}
		}
		
		T objeto = this.getRes().listById(id);
		model.addAttribute("objeto", objeto);
		
		return this.caminho+"/cadastro";
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, Model model) throws Exception {
		
		if (this.validacaoExcluir(id, model)) {
			this.getRes().deletar(id);
			return "redirect:/"+this.caminho+"/pesquisa";
		} else {
			return this.caminho+"/pesquisa";
		}
		
	}
	
	public abstract Long getId(T objeto);
	
	private boolean ehNovo(T objeto) {
		
		Long id = this.getId(objeto);
		
		if (id==null || id.equals(0)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public abstract boolean validacaoGravar(T objeto, Model model);
	public abstract boolean validacaoAlterar(T objeto, Model model);
	public abstract boolean validacaoExcluir(Long id, Model model);
	
}
