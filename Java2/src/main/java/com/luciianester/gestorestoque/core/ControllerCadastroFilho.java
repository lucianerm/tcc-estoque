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

	public ControllerCadastroFilho(String caminho, ResourceGenerico<T> resource) {
		super(caminho, resource);
	}

	@RequestMapping("")
	public String pesquisa(@PathVariable("paiId") Long paiId, Model model) throws Exception {
		
		this.paiId = paiId;
		this.model = model;
		this.pesquisar();
		
		return this.getCaminho()+"/pesquisa";
		
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(@PathVariable("paiId") Long paiId, @RequestParam(required=false) String tipo, Model model) throws Exception {
		
		this.paiId = paiId;
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();
			}
		}
		
		this.cadastrar();
		
		return this.getCaminho()+"/cadastro";
		
	}
	
	@RequestMapping("/{id}")
	public String edita(@PathVariable("paiId") Long paiId, @RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model model) throws Exception {
		
		this.paiId = paiId;
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();
			}
		}
		
		this.model = model;
		this.editar(id);
		
		return this.getCaminho()+"/cadastro";
		
	}
	
	@RequestMapping("/gravar")
	public String gravar(@PathVariable("paiId") Long paiId, @ModelAttribute("objeto") T objeto, Model model) throws Exception {
		
		this.paiId = paiId;
		
		this.model = model;
		return salvar(objeto);
		
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclui(@PathVariable("paiId") Long paiId, @PathVariable("id") Long id, Model model) throws Exception {
		
		this.paiId = paiId;
		
		return this.excluir(id);
		
	}

		
}
