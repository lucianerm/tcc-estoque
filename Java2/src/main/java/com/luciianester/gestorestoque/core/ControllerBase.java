package com.luciianester.gestorestoque.core;

import java.util.List;

import org.springframework.ui.Model;

public abstract class ControllerBase<T> {

	private String caminho;
	private ResourceGenerico<T> resource;
	protected Model model;

	public String getCaminho() {
		return caminho;
	}
	
	public ResourceGenerico<T> getRes() {
		return resource;
	}
	
	public Model getModel() {
		return model;
	}
	
	public ControllerBase(String caminho, ResourceGenerico<T> resource) {
		this.caminho = caminho;
		this.resource = resource;
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
	public Long getId(T objeto);

	abstract 
	public void pesquisar() throws Exception;
	
	abstract 
	public void cadastrar() throws Exception;
	
	abstract 
	public void editar(Long id) throws Exception;
	
	abstract 
	public String salvar(T objeto) throws Exception;
	
	abstract 
	public String excluir(Long id) throws Exception;
	
}
