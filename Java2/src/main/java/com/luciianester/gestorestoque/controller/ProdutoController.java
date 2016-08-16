package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ModelUtils;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaDoProduto;

@Controller
@RequestMapping("/produto")
public class ProdutoController extends ControllerGenerico<Produto>{

	public ProdutoController() {
		super("produto", new ResourceGenerico<Produto>(Produto.class));
	}

	@Override
	public Long getId(Produto objeto) {
		
		return objeto.getProdutoId();
	}

	@Override
	public boolean validacaoGravar(Produto objeto) {
		ModelUtils modelUtils = new ModelUtils(this.getModel());
		if(objeto.getDescricao() == null || objeto.getDescricao().equals("") ){
			modelUtils.setMensagemErro("Campo descrição é obrigatório.");
			return false;
		}else if (objeto.getDescricao().length() <3){
				modelUtils.setMensagemErro("Campo descrição deve ser maior que três caracteres.");
				return false;
			}
					
		return true;
	}

	@Override
	public boolean validacaoAlterar(Produto objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Long id) {
		return true;
	}
	/*
	@RequestMapping("/{id}/unidadedemedida")
	public String unidadeDeMedida(@RequestParam(required=false) String tipo, @PathVariable("id") Long id, Model model) throws Exception {
		
		if (tipo!=null) {
			if (tipo.equals(MensagemTipo.SALVOU_SUCESSO.toString())) {
				new ModelUtils(model).setMensagemSalvouSucesso();
			}
		}
		
		Produto objeto = this.getRes().listarPeloId(id);
		model.addAttribute("objeto", objeto);
		
		return this.caminho+"/unidadedemedida";
		
	}
	*/
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{id}/unidades", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody UnidadeDeMedidaDoProduto unidades(@PathVariable("id") Long id) throws Exception {
		
		UnidadeDeMedidaDoProduto unidadeDeMedidaDoProduto = new UnidadeDeMedidaDoProduto();
		
		Produto produto = new ResourceGenerico<Produto>(Produto.class).listarPeloId(id);
		
		List<UnidadeDeMedida> lista = this.getRes().getDao().getSessao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", produto))
				.addOrder(Order.asc("quantidade"))
				.list();
		
		unidadeDeMedidaDoProduto.setLista(lista);
		
		return unidadeDeMedidaDoProduto;
		
	}
	
	
}
