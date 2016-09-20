package com.luciianester.gestorestoque.base;

import java.util.List;

import org.springframework.ui.Model;

public abstract class ControleBase<T> {

	private String caminho;
	
	protected Model modelo;

	public String getCaminho() {
		
		return caminho;
		
	}
	
	public RecursoGenerico<T> criaRecurso() {
		
		RecursoGenerico<T> recurso = novoRecurso();
		recurso.setModelo(this.modelo);
		
		return recurso;
		
	}
	
	public Model getModelo() {
		return modelo;
	}
	
	public ControleBase(String caminho) {
		this.caminho = caminho;
	}

	protected void addAtributo(String nome, Object objeto) {
		this.getModelo().addAttribute(nome, objeto);
	}
	
	protected void setLista(List<T> lista) {
		this.addAtributo("lista", lista);
	}
	
	protected void setObjeto(T objeto) {
		this.addAtributo("objeto", objeto);
	}
	
	public void setMensagem(MensagemTipo tipo, String texto) {
		this.modelo.addAttribute("mensagemTipo", tipo);
		this.modelo.addAttribute("mensagem", texto);
	}
	
	public void setMensagemErro(String texto) {
		this.setMensagem(MensagemTipo.ERRO, texto);
	}
	
	abstract 
	public void pesquisar(RecursoGenerico<T> resource) throws Exception;
	
	abstract 
	public void cadastrar(RecursoGenerico<T> resource) throws Exception;
	
	abstract 
	public void editar(RecursoGenerico<T> resource, Long id) throws Exception;
	
	abstract 
	public String salvar(RecursoGenerico<T> resource, T objeto) throws Exception;
	
	abstract 
	public String excluir(RecursoGenerico<T> resource, Long id) throws Exception;
	
	abstract
	public RecursoGenerico<T> novoRecurso();
	
}
