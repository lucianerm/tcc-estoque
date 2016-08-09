package com.luciianester.gestorestoque.core;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ControllerCadastro<T> extends ControllerBase<T> {

	public ControllerCadastro(String caminho, ResourceGenerico<T> resource) {
		super(caminho, resource);
	}

	@RequestMapping("")
	public String pesquisa(Model model) throws Exception {
		
		this.model = model;
		this.pesquisar();
		
		return this.getCaminho()+"/pesquisa";
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(@RequestParam(required=false) String tipo, Model model) throws Exception {
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();
			}
		}
		
		this.cadastrar();
		
		return this.getCaminho()+"/cadastro";
		
	}
	
	@RequestMapping("/{id}")
	public String edita(@RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model model) throws Exception {
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();
			}
		}
		
		this.model = model;
		this.editar(id);
		
		return this.getCaminho()+"/cadastro";
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@ModelAttribute("objeto") T objeto, Model model) throws Exception {
		
		this.model = model;
		return salvar(objeto);
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("id") Long id, Model model) throws Exception {
		
		return this.excluir(id);
		
	}
	
}
