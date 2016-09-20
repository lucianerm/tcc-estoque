package com.luciianester.gestorestoque.base;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ControleCadastro<T> extends ControleBase<T> {

	public ControleCadastro(String caminho) {
		super(caminho);
	}

	@RequestMapping("")
	public String pesquisa(Model modelo) throws Exception {
		
		try (RecursoGenerico<T> rescurso = this.criaRecurso();) {
			
			this.modelo = modelo;
			this.pesquisar(rescurso);
			
			return this.getCaminho()+"/pesquisa";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(@RequestParam(required=false) String tipo, Model modelo) throws Exception {
		
		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.modelo = modelo;
			
			if (tipo!=null) {
				if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
					new ModelUtils(modelo).setMensagemSalvouSucesso();
				}
			}
			
			this.cadastrar(recurso);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/{id}")
	public String edita(@RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.modelo = modelo;
			
			if (tipo!=null) {
				if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
					new ModelUtils(modelo).setMensagemSalvouSucesso();
				}
			}
			
			this.editar(recurso, id);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@ModelAttribute("objeto") T objeto, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.modelo = modelo;
			return salvar(recurso, objeto);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("id") Long id, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			return this.excluir(recurso, id);
			
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
}
