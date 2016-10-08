package com.luciianester.gestorestoque.base;

public abstract class ControleBase<T> extends UtilidadesDoModelo {

	private String caminho;
	
	public String getCaminho() {
		
		return caminho;
		
	}
	
	public RecursoGenerico<T> criaRecurso() {
		
		RecursoGenerico<T> recurso = novoRecurso();
		recurso.setModelo(this.getModelo());
		
		return recurso;
		
	}
	
	public ControleBase(String caminho) {
		this.caminho = caminho;
	}

	abstract 
	public void pesquisar(RecursoGenerico<T> recurso) throws Exception;
	
	abstract 
	public void cadastrar(RecursoGenerico<T> recurso) throws Exception;
	
	abstract 
	public void editar(RecursoGenerico<T> recurso, Long id) throws Exception;
	
	abstract 
	public String salvar(RecursoGenerico<T> recurso, T objeto) throws Exception;
	
	abstract 
	public String excluir(RecursoGenerico<T> recurso, Long id) throws Exception;
	
	abstract
	public RecursoGenerico<T> novoRecurso();
	
}
