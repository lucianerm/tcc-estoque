package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.EntradaItem;
import com.luciianester.gestorestoque.model.Saida;
import com.luciianester.gestorestoque.model.SaidaItem;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;
import com.luciianester.gestorestoque.resources.entrada.item.EntradaItemResources;
import com.luciianester.gestorestoque.resources.saida.SaidaResources;
import com.luciianester.gestorestoque.resources.saida.item.SaidaItemResources;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaResources;

@Controller
@RequestMapping("/saida/{paiId}/saidaitem")
public class SaidaItemController extends ControllerCadastroFilho<SaidaItem> {

	private Saida saida = null;
	
	public SaidaItemController() {
		super("saidaitem");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(ResourceGenerico<SaidaItem> resource) throws Exception {
		
		this.setObjeto(new SaidaItem());
		
		this.saida = new SaidaResources(resource.getDao()).listarPeloId(this.getPaiId());
		this.addAttribute("saida", this.saida);
		
		@SuppressWarnings("unchecked")
		List<SaidaItem> lista = (List<SaidaItem>) resource.getDao().createCriteria(SaidaItem.class)
				.add(Restrictions.eq("saida", this.saida))
				.addOrder(Order.asc("saidaItemId"))
				.list();
		this.setLista(lista);
		
		List<EntradaItem> listProdutos = new EntradaItemResources(resource.getDao()).listarTodos();
		this.addAttribute("listProdutos", listProdutos);
		
		List<UnidadeDeMedida> listUnidadeDeMedida = new ArrayList<>();
		this.addAttribute("listUnidadeDeMedida", listUnidadeDeMedida);
		
	}

	@Override
	public void cadastrar(ResourceGenerico<SaidaItem> resource) throws Exception {
		this.pesquisar(resource);
	}

	@Override
	public void editar(ResourceGenerico<SaidaItem> resource, Long id) throws Exception {
		
		this.pesquisar(resource);
		
		SaidaItem saidaItem = resource.listarPeloId(id);
		this.setObjeto(saidaItem);
		
		List<UnidadeDeMedida> listUnidadeDeMedida = new ArrayList<>();
		
		if (saidaItem.getEntradaItem()!=null && saidaItem.getEntradaItem().getEntradaItemId()!=null) {
			listUnidadeDeMedida = new UnidadeDeMedidaResources(resource.getDao()).listarPeloItemDaEntrada(saidaItem.getEntradaItem().getEntradaItemId()).getLista();
		}
		
		this.addAttribute("listUnidadeDeMedida", listUnidadeDeMedida);
		
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(ResourceGenerico<SaidaItem> resource, SaidaItem objeto) throws Exception {
		
		this.saida = new SaidaResources(resource.getDao()).listarPeloId(this.getPaiId());
		
		objeto.setSaida(this.saida);
		
		if (resource.ehNovo(objeto)) {
			resource.gravar(objeto);
		} else {
			resource.alterar(objeto);
		}
		
		return "redirect:/saida/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(ResourceGenerico<SaidaItem> resource, Long id) throws Exception {
		resource.remover(id);
		return "redirect:/saida/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public ResourceGenerico<SaidaItem> newResource() {
		return new SaidaItemResources(new DAO());
	}

}
