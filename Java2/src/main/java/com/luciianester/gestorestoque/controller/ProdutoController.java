package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;

@Controller
@RequestMapping("/produto")
public class ProdutoController extends ControllerGenerico<Produto>{

	public ProdutoController() {
		super("produto", new ProdutoResources());
	}

	/*
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{id}/unidades", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody UnidadeDeMedidaDoProduto unidades(@PathVariable("id") Long id) throws Exception {
		
		UnidadeDeMedidaDoProduto unidadeDeMedidaDoProduto = new UnidadeDeMedidaDoProduto();
		
		Produto produto = this.getRes().listarPeloId(id);
		
		List<UnidadeDeMedida> lista = this.getRes().getDao().getSessao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", produto))
				.addOrder(Order.asc("quantidade"))
				.list();
		
		unidadeDeMedidaDoProduto.setLista(lista);
		
		return unidadeDeMedidaDoProduto;
		
	}
*/
}
