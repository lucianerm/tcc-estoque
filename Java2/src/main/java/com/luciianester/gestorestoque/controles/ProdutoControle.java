package com.luciianester.gestorestoque.controles;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Categoria;
import com.luciianester.gestorestoque.entidades.Marca;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.recursos.categoria.CategoriaRecurso;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemDoProduto;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.marca.MarcaRecurso;
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
	
	@RequestMapping("/voltar")
	public String voltarProduto(HttpSession sessao) {
		
		String redirecionar = "/entrada/1/entradaitem/1";
		
		try {
			if ("entradaitem".equals(sessao.getAttribute("voltarTelaEntradaitem"))) {
				Long entradaId = Long.valueOf(""+sessao.getAttribute("entradaitemPaiId"));
				Long entradaItemId = Long.valueOf(""+sessao.getAttribute("entradaitemId"));
				
				redirecionar = "/entrada/"+entradaId+"/entradaitem";
				if (entradaItemId>0) {
					redirecionar += "/"+entradaItemId;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sessao.setAttribute("voltarTelaEntradaitem", null);
		
		return "redirect:"+redirecionar;
		
	}
	
	@Override
	public void pesquisar(RecursoGenerico<Produto> recurso) throws Exception {
		
		@SuppressWarnings("resource")
		List<Marca> listaMarcas = new MarcaRecurso(recurso.getDao()).listarTodos();
		this.addAtributo("listaMarcas", listaMarcas);
		
		@SuppressWarnings("resource")
		List<Categoria> listaCategorias = new CategoriaRecurso(recurso.getDao()).listarTodos();
		this.addAtributo("listaCategorias", listaCategorias);
		
		super.pesquisar(recurso);
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
	
	@RequestMapping("/novamarca/{produtoId}")
	public String novaMarca(@PathVariable("produtoId") Long produtoId, HttpSession sessao) {
		
		sessao.setAttribute("voltarTelaProduto", "marca");
		sessao.setAttribute("marcaProdutoId", produtoId);
		
		return "redirect:/marca/cadastro";
		
	}
	
	@RequestMapping("/novacategoria/{produtoId}")
	public String novaCategoria(@PathVariable("produtoId") Long produtoId, HttpSession sessao) {
		
		sessao.setAttribute("voltarTelaProduto", "categoria");
		sessao.setAttribute("categoriaProdutoId", produtoId);
		
		return "redirect:/categoria/cadastro";
		
	}

}
