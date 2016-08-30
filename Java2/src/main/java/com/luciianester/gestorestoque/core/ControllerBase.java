package com.luciianester.gestorestoque.core;

import java.util.List;

import org.springframework.ui.Model;

public abstract class ControllerBase<T> {

	private String caminho;
	
	protected Model model;

	public String getCaminho() {
		
		return caminho;
		
	}
	
	public ResourceGenerico<T> createResource() {
		
		ResourceGenerico<T> resource = newResource();
		resource.setModel(this.model);
		
		return resource;
		
	}
	
	public Model getModel() {
		return model;
	}
	
	public ControllerBase(String caminho) {
		this.caminho = caminho;
	}

	protected void addAttribute(String nome, Object objeto) {
		this.getModel().addAttribute(nome, objeto);
	}
	
	protected void setLista(List<T> lista) {
		this.addAttribute("lista", lista);
	}
	
	protected void setObjeto(T objeto) {
		this.addAttribute("objeto", objeto);
	}
	
	public void setMensagem(MensagemTipo tipo, String texto) {
		this.model.addAttribute("mensagemTipo", tipo);
		this.model.addAttribute("mensagem", texto);
	}
	
	public void setMensagemErro(String texto) {
		this.setMensagem(MensagemTipo.ERRO, texto);
	}
	
	abstract 
	public void pesquisar(ResourceGenerico<T> resource) throws Exception;
	
	abstract 
	public void cadastrar(ResourceGenerico<T> resource) throws Exception;
	
	abstract 
	public void editar(ResourceGenerico<T> resource, Long id) throws Exception;
	
	abstract 
	public String salvar(ResourceGenerico<T> resource, T objeto) throws Exception;
	
	abstract 
	public String excluir(ResourceGenerico<T> resource, Long id) throws Exception;
	
	abstract
	public ResourceGenerico<T> newResource();
	
}
