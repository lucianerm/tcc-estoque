package com.luciianester.gestorestoque.base;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ControleCadastroFilho<T> extends ControleBase<T> {

	private Long paiId;

	public Long getPaiId() {
		return paiId;
	}

	public ControleCadastroFilho(String caminho) {
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
	public String pesquisa(@PathVariable("paiId") Long paiId, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.paiId = paiId;
			this.setModelo(modelo);
			this.pesquisar(recurso);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(@PathVariable("paiId") Long paiId, @RequestParam(required=false) String tipo, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.paiId = paiId;
			this.verificaMensagemSucesso(tipo);
			this.cadastrar(recurso);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/{id}")
	public String edita(@PathVariable("paiId") Long paiId, @RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.paiId = paiId;
			this.verificaMensagemSucesso(tipo);
			this.setModelo(modelo);
			this.editar(recurso, id);
			
			return this.getCaminho()+"/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@PathVariable("paiId") Long paiId, @ModelAttribute("objeto") T objeto, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.paiId = paiId;
			
			this.setModelo(modelo);
			return salvar(recurso, objeto);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("paiId") Long paiId, @PathVariable("id") Long id, Model modelo) throws Exception {

		try (RecursoGenerico<T> recurso = this.criaRecurso();) {
			
			this.paiId = paiId;
			
			return this.excluir(recurso, id);
			
		} catch (Exception e) {
			throw e;
		}
		
	}

		
}
