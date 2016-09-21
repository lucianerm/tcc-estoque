package com.luciianester.gestorestoque.controles;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemDoProduto;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaDoProduto;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaRecurso;

@Controller
@RequestMapping("/produto")
public class ProdutoControle extends ControleGenerico<Produto>{

	public ProdutoControle() {
		super("produto");
	}

	@Override
	public RecursoGenerico<Produto> novoRecurso() {
		return new ProdutoRecurso(new DAO());
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/{id}/unidades", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody UnidadeDeMedidaDoProduto unidades(@PathVariable("id") Long id) throws Exception {
		
		return new UnidadeDeMedidaRecurso(this.criaRecurso().getDao()).listarPeloProduto(id);
		 	
	}

	@SuppressWarnings("resource")
	@RequestMapping(value="/{id}/unidadesdaentrada", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody UnidadeDeMedidaDoProduto unidadesPeloItemDaEntrada(@PathVariable("id") Long id) throws Exception {
		
		return new UnidadeDeMedidaRecurso(this.criaRecurso().getDao()).listarPeloItemDaEntrada(id);
		 	
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/{id}/entradas", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody EntradaItemDoProduto entradasPeloProduto(@PathVariable("id") Long id) throws Exception {
		
		return new EntradaItemRecurso(this.criaRecurso().getDao()).listarPeloProduto(id);
		 	
	}

}
