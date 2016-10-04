package com.luciianester.gestorestoque.base;

import java.util.List;

public abstract class ControleGenerico<T> extends ControleCadastro<T> {

	public ControleGenerico(String caminho) {
		super(caminho);
	}

	@Override
	public void pesquisar(RecursoGenerico<T> recurso) throws Exception {

		List<T> lista = recurso.listarTodos();
		this.setLista(lista);
		
	}
	
	@Override
	public void cadastrar(RecursoGenerico<T> recurso) throws Exception {
		
		T objeto = recurso.criaNovoObjeto();
		this.setObjeto(objeto);
		
	}
	
	@Override
	public void editar(RecursoGenerico<T> recurso, Long id) throws Exception {
		
		T objeto = recurso.listarPeloId(id);
		this.setObjeto(objeto);
		
	}
	
	private String retornaTela(RecursoGenerico<T> recurso, T objeto) throws Exception {
		this.pesquisar(recurso);
		this.setObjeto(objeto);
		return this.getCaminho()+"/cadastro";
	}
	
	@Override
	public String salvar(RecursoGenerico<T> recurso, T objeto) throws Exception {
		
		try {
		
			if (recurso.verificaNovoCadastro(objeto)) {
				if (!recurso.gravar(objeto)) {
					return this.retornaTela(recurso, objeto);
				}
			} else {
				if (!recurso.alterar(objeto)) {
					return this.retornaTela(recurso, objeto);
				}
			}
			
			return "redirect:/"+this.getCaminho()+"/"+recurso.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
			
		} catch (Exception e) {
			
			this.addAtributo("tipo", MensagemTipo.ERRO);
			this.addAtributo("mensagem", e.getMessage());
			
			this.setObjeto(objeto);
			
			return this.getCaminho()+"/cadastro";
			
		}
			
	}
	
	@Override
	public String excluir(RecursoGenerico<T> recurso, Long id) throws Exception {
		
		if (recurso.remover(id)) {
			return "redirect:/"+this.getCaminho();
		} else {
			this.pesquisar(recurso);
			return this.getCaminho()+"/pesquisa";
		}
		
	}
	
}
