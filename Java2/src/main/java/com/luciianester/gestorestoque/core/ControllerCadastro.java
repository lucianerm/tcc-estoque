package com.luciianester.gestorestoque.core;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ControllerCadastro<T> extends ControllerBase<T> {

	public ControllerCadastro(String caminho) {
		super(caminho);
	}

	@RequestMapping("")
	public String pesquisa(Model model) throws Exception {
		
		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.model = model;
			this.pesquisar(resource);
			
			return this.getCaminho()+"/pesquisa";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(@RequestParam(required=false) String tipo, Model model) throws Exception {
		
		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.model = model;
			
			if (tipo!=null) {
				if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
					new ModelUtils(model).setMensagemSalvouSucesso();
				}
			}
			
			this.cadastrar(resource);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/{id}")
	public String edita(@RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.model = model;
			
			if (tipo!=null) {
				if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
					new ModelUtils(model).setMensagemSalvouSucesso();
				}
			}
			
			this.editar(resource, id);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@ModelAttribute("objeto") T objeto, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.model = model;
			return salvar(resource, objeto);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("id") Long id, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			return this.excluir(resource, id);
			
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
}
