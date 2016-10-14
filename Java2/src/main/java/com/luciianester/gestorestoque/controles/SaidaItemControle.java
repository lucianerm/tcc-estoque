package com.luciianester.gestorestoque.controles;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleCadastroFilho;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.entidades.Saida;
import com.luciianester.gestorestoque.entidades.SaidaItem;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemDoProduto;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.saida.SaidaRecurso;
import com.luciianester.gestorestoque.recursos.saida.item.SaidaItemRecurso;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaDoProduto;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaRecurso;

@Controller
@RequestMapping("/saida/{paiId}/saidaitem")
public class SaidaItemControle extends ControleCadastroFilho<SaidaItem> {

	private Saida saida = null;
	
	public SaidaItemControle() {
		super("saidaitem");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(RecursoGenerico<SaidaItem> recurso) throws Exception {
		
		this.setObjeto(new SaidaItem());
		
		this.saida = new SaidaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		this.addAtributo("saida", this.saida);
		
		@SuppressWarnings("unchecked")
		List<SaidaItem> lista = (List<SaidaItem>) recurso.getDao().createCriteria(SaidaItem.class)
				.add(Restrictions.eq("saida", this.saida))
				.addOrder(Order.asc("saidaItemId"))
				.list();
		this.setLista(lista);
		
		List<Produto> listaProdutos = new ProdutoRecurso(recurso.getDao()).listarTodos();
		this.addAtributo("listaProdutos", listaProdutos);
		
		List<EntradaItem> listaEntradaItem = new ArrayList<>();
		this.addAtributo("listaEntradaItem", listaEntradaItem);
		
		List<UnidadeDeMedida> listaUnidadeDeMedida = new ArrayList<>();
		this.addAtributo("listaUnidadeDeMedida", listaUnidadeDeMedida);
		
		this.addAtributo("totalSaida", new SaidaRecurso(recurso.getDao()).calcularTotal(this.getPaiId()));
		
	}

	@Override
	public void cadastrar(RecursoGenerico<SaidaItem> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@SuppressWarnings("resource")
	private void editar(RecursoGenerico<SaidaItem> recurso, SaidaItem saidaItem) throws Exception {

		this.pesquisar(recurso);
		
		EntradaItem entradaItem = new EntradaItemRecurso(recurso.getDao()).listarPeloId(saidaItem.getEntradaItem().getEntradaItemId());
		
		if (entradaItem!=null) {
			saidaItem.setProdutoId(entradaItem.getProduto().getProdutoId());
		}
		this.setObjeto(saidaItem);
		
		EntradaItemDoProduto listarPeloProduto = new EntradaItemRecurso(recurso.getDao())
				.listarPeloProduto(saidaItem.getProdutoId());
		this.addAtributo("listaEntradaItem", listarPeloProduto.getLista());
		
		if (saidaItem.getEntradaItem()!=null && saidaItem.getEntradaItem().getEntradaItemId()!=null && saidaItem.getEntradaItem().getEntradaItemId()>0) {
			UnidadeDeMedidaDoProduto listarPeloItemDaEntrada = new UnidadeDeMedidaRecurso(recurso.getDao())
				.listarPeloItemDaEntrada(saidaItem.getEntradaItem().getEntradaItemId());
			this.addAtributo("listaUnidadeDeMedida", listarPeloItemDaEntrada.getLista());
		}
		
		
	}
	
	@Override
	public void editar(RecursoGenerico<SaidaItem> recurso, Long id) throws Exception {
		
		
		SaidaItem saidaItem = recurso.listarPeloId(id);
		this.editar(recurso, saidaItem);
		
	}

	@Override
	public String salvar(RecursoGenerico<SaidaItem> recurso, SaidaItem objeto) throws Exception {
		
		this.editar(recurso, objeto);
		objeto.setSaida(this.saida);
		
		if (!this.salvarOuAlterar(recurso, objeto)) {
			return this.getCaminho()+"/cadastro";
		}
		
		return "redirect:/saida/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(RecursoGenerico<SaidaItem> recurso, Long id) throws Exception {
		recurso.remover(id);
		return "redirect:/saida/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public RecursoGenerico<SaidaItem> novoRecurso() {
		return new SaidaItemRecurso(new DAO());
	}

}
