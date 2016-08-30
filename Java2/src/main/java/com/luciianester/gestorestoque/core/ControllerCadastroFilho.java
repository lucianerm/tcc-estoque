package com.luciianester.gestorestoque.core;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ControllerCadastroFilho<T> extends ControllerBase<T> {

	private Long paiId;

	public Long getPaiId() {
		return paiId;
	}

	public ControllerCadastroFilho(String caminho) {
		super(caminho);
	}

	@RequestMapping("")
	public String pesquisa(@PathVariable("paiId") Long paiId, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.paiId = paiId;
			this.model = model;
			this.pesquisar(resource);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(@PathVariable("paiId") Long paiId, @RequestParam(required=false) String tipo, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.paiId = paiId;
			
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
	public String edita(@PathVariable("paiId") Long paiId, @RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.paiId = paiId;
			
			if (tipo!=null) {
				if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
					new ModelUtils(model).setMensagemSalvouSucesso();
				}
			}
			
			this.model = model;
			this.editar(resource, id);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@PathVariable("paiId") Long paiId, @ModelAttribute("objeto") T objeto, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.paiId = paiId;
			
			this.model = model;
			return salvar(resource, objeto);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("paiId") Long paiId, @PathVariable("id") Long id, Model model) throws Exception {

		try (ResourceGenerico<T> resource = this.createResource();) {
			
			this.paiId = paiId;
			
			return this.excluir(resource, id);
			
		} catch (Exception e) {
			throw e;
		}
		
	}

		
}
