package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.MensagemTipo;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Entrada;
import com.luciianester.gestorestoque.model.EntradaItem;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;
import com.luciianester.gestorestoque.resources.entrada.EntradaResources;
import com.luciianester.gestorestoque.resources.entrada.item.EntradaItemResources;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaResources;

@Controller
@RequestMapping("/entrada/{paiId}/entradaitem")
public class EntradaItemController extends ControllerCadastroFilho<EntradaItem>{

	private Entrada entrada = null;
	
	public EntradaItemController() {
		super("entradaitem");
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public void pesquisar(ResourceGenerico<EntradaItem> resource) throws Exception {

		this.entrada = new EntradaResources(resource.getDao()).listarPeloId(this.getPaiId());
		this.addAttribute("entrada", entrada);

		EntradaItem entradaItem = new EntradaItem();
		entradaItem.setEntrada(this.entrada);
		this.setObjeto(entradaItem);
		
		List<EntradaItem> lista = resource.getDao()
				.createCriteria(EntradaItem.class)
				.add(Restrictions.eq("entrada", this.entrada))
				.addOrder(Order.asc("entradaItemId"))
				.list();
		
		this.setLista(lista);

		
		List<Produto> listProdutos = new ProdutoResources(resource.getDao()).listarTodos();
		this.addAttribute("listProdutos", listProdutos);
		
		List<UnidadeDeMedida> listUnidadeDeMedida = new ArrayList<>();
		this.addAttribute("listUnidadeDeMedida", listUnidadeDeMedida);
		
	}

	@Override
	public void cadastrar(ResourceGenerico<EntradaItem> resource) throws Exception {
		this.pesquisar(resource);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void editar(ResourceGenerico<EntradaItem> resource, Long id) throws Exception {
		
		this.pesquisar(resource);
		
		EntradaItem entradaItem = resource.listarPeloId(id);
		this.setObjeto(entradaItem);
		
		List<UnidadeDeMedida> listUnidadeDeMedida = new ArrayList<>();
		
		if (entradaItem.getProduto()!=null && entradaItem.getProduto().getProdutoId()!=null) {
			listUnidadeDeMedida = new UnidadeDeMedidaResources(resource.getDao()).getDao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", entradaItem.getProduto()))
				.addOrder(Order.asc("descricao"))
				.list();
		}
		
		this.addAttribute("listUnidadeDeMedida", listUnidadeDeMedida);
		
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(ResourceGenerico<EntradaItem> resource, EntradaItem objeto) throws Exception {
		
		this.entrada = new EntradaResources(resource.getDao()).listarPeloId(this.getPaiId());
		objeto.setEntrada(this.entrada);
		this.setObjeto(objeto);
		
		if (objeto.getEntradaItemId()==null) {
			resource.gravar(objeto);
		} else {
			resource.alterar(objeto);
		}
		
		return "redirect:/entrada/"+this.getPaiId()+"/"+this.getCaminho()+"/"+resource.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
		
	}

	@Override
	public String excluir(ResourceGenerico<EntradaItem> resource, Long id) throws Exception {
		
		resource.remover(id);
		return "redirect:/entrada/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public ResourceGenerico<EntradaItem> newResource() {
		return new EntradaItemResources(new DAO());
	}
	
}
