package com.luciianester.gestorestoque.base;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ControleCadastro<T> extends ControleBase<T> {

	private String campoPesquisa = null;
	
	public ControleCadastro(String caminho) {
		super(caminho);
	}
	
	private void verificaMensagemSucesso(String tipo) {
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				this.setMensagemSalvouSucesso();
			}
		}
	}
	
	@RequestMapping("")
	public String pesquisa(Model modelo, @RequestParam(required=false) String campoPesquisa) throws Exception {
		
		this.setModelo(modelo);
		
		this.setCampoPesquisa(campoPesquisa);
		
		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.pesquisar(recurso);
			
			return this.getCaminho()+"/pesquisa";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(Model modelo) throws Exception {
		
		this.setModelo(modelo);
		
		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.pesquisar(recurso);
			this.cadastrar(recurso);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/{id}")
	public String edita(@RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model modelo) throws Exception {
		
		this.setModelo(modelo);

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.verificaMensagemSucesso(tipo);
			this.pesquisar(recurso);
			this.editar(recurso, id);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@ModelAttribute("objeto") T objeto, Model modelo) throws Exception {
		
		this.setModelo(modelo);

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.pesquisar(recurso);
			return salvar(recurso, objeto);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("id") Long id, Model modelo) throws Exception {

		this.setModelo(modelo);
		
		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.pesquisar(recurso);
			return this.excluir(recurso, id);
			
		} catch (Exception e) {
			throw e;
		}
		
		
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		if (campoPesquisa==null) {
			campoPesquisa = "";
		}
		campoPesquisa = campoPesquisa.replace("%", "");
		this.addAtributo("campoPesquisa", campoPesquisa);
		this.campoPesquisa = campoPesquisa;
	}
	
}
