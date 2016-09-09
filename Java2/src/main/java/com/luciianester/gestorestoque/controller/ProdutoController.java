package com.luciianester.gestorestoque.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luciianester.gestorestoque.core.ControllerGenerico;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaDoProduto;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaResources;

@Controller
@RequestMapping("/produto")
public class ProdutoController extends ControllerGenerico<Produto>{

	public ProdutoController() {
		super("produto");
	}

	@Override
	public ResourceGenerico<Produto> newResource() {
		return new ProdutoResources(new DAO());
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/{id}/unidades", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody UnidadeDeMedidaDoProduto unidades(@PathVariable("id") Long id) throws Exception {
		
		return new UnidadeDeMedidaResources(this.createResource().getDao()).listarPeloProduto(id);
		 	
	}

	@SuppressWarnings("resource")
	@RequestMapping(value="/{id}/unidadesdaentrada", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody UnidadeDeMedidaDoProduto unidadesPeloItemDaEntrada(@PathVariable("id") Long id) throws Exception {
		
		return new UnidadeDeMedidaResources(this.createResource().getDao()).listarPeloItemDaEntrada(id);
		 	
	}

}
