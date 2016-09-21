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
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.saida.SaidaRecurso;
import com.luciianester.gestorestoque.recursos.saida.item.SaidaItemRecurso;
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
		
	}

	@Override
	public void cadastrar(RecursoGenerico<SaidaItem> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@Override
	public void editar(RecursoGenerico<SaidaItem> recurso, Long id) throws Exception {
		
		this.pesquisar(recurso);
		
		SaidaItem saidaItem = recurso.listarPeloId(id);
		this.setObjeto(saidaItem);
		
		List<UnidadeDeMedida> listaUnidadeDeMedida = new ArrayList<>();
		
		if (saidaItem.getEntradaItem()!=null && saidaItem.getEntradaItem().getEntradaItemId()!=null) {
			listaUnidadeDeMedida = new UnidadeDeMedidaRecurso(recurso.getDao()).listarPeloItemDaEntrada(saidaItem.getEntradaItem().getEntradaItemId()).getLista();
		}
		
		this.addAtributo("listaUnidadeDeMedida", listaUnidadeDeMedida);
		
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(RecursoGenerico<SaidaItem> recurso, SaidaItem objeto) throws Exception {
		
		this.saida = new SaidaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		
		objeto.setSaida(this.saida);
		
		if (recurso.verificaNovoCadastro(objeto)) {
			recurso.gravar(objeto);
		} else {
			recurso.alterar(objeto);
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
