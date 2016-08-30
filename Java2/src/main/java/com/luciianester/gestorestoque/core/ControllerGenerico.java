package com.luciianester.gestorestoque.core;

import java.util.List;

public abstract class ControllerGenerico<T> extends ControllerCadastro<T> {

	public ControllerGenerico(String caminho) {
		super(caminho);
	}

	@Override
	public void pesquisar(ResourceGenerico<T> resource) throws Exception {

		List<T> lista = resource.listarTodos();
		this.setLista(lista);
		
	}
	
	@Override
	public void cadastrar(ResourceGenerico<T> resource) throws Exception {
		
		T objeto = resource.newInstance();
		this.setObjeto(objeto);
		
	}
	
	@Override
	public void editar(ResourceGenerico<T> resource, Long id) throws Exception {
		
		T objeto = resource.listarPeloId(id);
		this.setObjeto(objeto);
		
	}
	
	@Override
	public String salvar(ResourceGenerico<T> resource, T objeto) throws Exception {
		
		try {
		
			if (resource.ehNovo(objeto)) {
				if (!resource.gravar(objeto)) {
					return this.getCaminho()+"/cadastro";
				}
			} else {
				if (!resource.alterar(objeto)) {
					return this.getCaminho()+"/cadastro";
				}
			}
			
			return "redirect:/"+this.getCaminho()+"/"+resource.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
			
		} catch (Exception e) {
			
			this.getModel().addAttribute("tipo", MensagemTipo.ERRO);
			this.getModel().addAttribute("mensagem", e.getMessage());
			
			this.setObjeto(objeto);
			
			return this.getCaminho()+"/cadastro";
			
		}
			
	}
	
	@Override
	public String excluir(ResourceGenerico<T> resource, Long id) throws Exception {
		
		if (resource.remover(id)) {
			return "redirect:/"+this.getCaminho()+"/pesquisa";
		} else {
			return this.getCaminho()+"/pesquisa";
		}
		
	}
	
}
