package com.luciianester.gestorestoque.core;

import java.util.List;

public abstract class ControllerGenerico<T> extends ControllerCadastro<T> {

	public ControllerGenerico(String caminho, ResourceGenerico<T> resource) {
		super(caminho, resource);
	}

	@Override
	public void pesquisar() throws Exception {

		List<T> lista = this.getRes().listarTodos();
		this.setLista(lista);
		
	}
	
	@Override
	public void cadastrar() throws Exception {
		
		T objeto = this.getRes().newInstance();
		this.setObjeto(objeto);
		
	}
	
	@Override
	public void editar(Long id) throws Exception {
		
		T objeto = this.getRes().listarPeloId(id);
		this.setObjeto(objeto);
		
	}
	
	@Override
	public String salvar(T objeto) throws Exception {
		
		try {
		
			if (this.ehNovo(objeto)) {
				if (this.validacaoGravar(objeto)) {
					this.getRes().gravar(objeto);
				} else {
					return this.getCaminho()+"/cadastro";
				}
			} else {
				if (this.validacaoAlterar(objeto)) {
					this.getRes().alterar(objeto);
				} else {
					return this.getCaminho()+"/cadastro";
				}
			}
			
			return "redirect:/"+this.getCaminho()+"/"+this.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
			
		} catch (Exception e) {
			
			this.getModel().addAttribute("tipo", MensagemTipo.ERRO);
			this.getModel().addAttribute("mensagem", e.getMessage());
			
			this.setObjeto(objeto);
			
			return this.getCaminho()+"/cadastro";
			
		}
			
	}
	
	@Override
	public String excluir(Long id) throws Exception {
		
		if (this.validacaoExcluir(id)) {
			this.getRes().remover(id);
			return "redirect:/"+this.getCaminho()+"/pesquisa";
		} else {
			return this.getCaminho()+"/pesquisa";
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
	
	public abstract boolean validacaoGravar(T objeto);
	public abstract boolean validacaoAlterar(T objeto);
	public abstract boolean validacaoExcluir(Long id);
	
}
