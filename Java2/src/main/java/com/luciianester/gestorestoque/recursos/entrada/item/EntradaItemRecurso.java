package com.luciianester.gestorestoque.recursos.entrada.item;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.entidades.SaidaItem;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.saida.item.SaidaItemRecurso;

public class EntradaItemRecurso extends RecursoGenerico<EntradaItem> {

	public EntradaItemRecurso(DAO dao) {
		super(EntradaItem.class, dao);
	}


	@Override
	public Long getId(EntradaItem objeto) {
		return objeto.getEntradaItemId();
	}
	
	@Override
	public boolean validacaoGravar(EntradaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(EntradaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoExcluir(EntradaItem objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	public void calculaSaldo(EntradaItem entradaItem) throws Exception {
		
		BigDecimal saldo = BigDecimal.ZERO;
		saldo = saldo.add(entradaItem.getQuantidade().multiply(BigDecimal.valueOf(entradaItem.getUnidadeDeMedida().getQuantidade())));
		
		@SuppressWarnings("resource")
		List<SaidaItem> listaSaidaItem = new SaidaItemRecurso(this.getDao()).listarPelaEntrada(entradaItem);
		
		for (SaidaItem saidaItem : listaSaidaItem) {
			saldo = saldo.subtract(saidaItem.getQuantidade().multiply(BigDecimal.valueOf(saidaItem.getUnidadeDeMedida().getQuantidade())));
		}
		
		entradaItem.setSaldo(saldo);
		
		UnidadeDeMedida unidadePrincipal = (UnidadeDeMedida) this.getDao().createCriteria(UnidadeDeMedida.class)
			.add(Restrictions.eq("produto", entradaItem.getProduto()))
			.add(Restrictions.eq("quantidade", 1))
			.uniqueResult();
		
		if (unidadePrincipal!=null) {
			entradaItem.setSigla(unidadePrincipal.getSigla());
		}
		
	}

	public EntradaItemDoProduto listarPeloProduto(Long id) {
		
		EntradaItemDoProduto doProduto = new EntradaItemDoProduto();
		
		try {
			
			@SuppressWarnings("resource")
			Produto produto = new ProdutoRecurso(this.getDao()).listarPeloId(id);
			
			@SuppressWarnings("unchecked")
			List<EntradaItem> listaEntradaItem = (List<EntradaItem>) this.getDao().createCriteria(EntradaItem.class)
				.add(Restrictions.eq("produto", produto))
				.list();
			
			for (EntradaItem entradaItem : listaEntradaItem) {
				this.calculaSaldo(entradaItem);
			}
			
			doProduto.setLista(listaEntradaItem);
			
		} catch (Exception e) {
			
		}
		
		return doProduto;
		
	}
	
	public List<EntradaItem> listarPelaEntrada(Entrada entrada) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<EntradaItem> lista = (List<EntradaItem>) this.getDao().createCriteria(EntradaItem.class)
				.add(Restrictions.eq("entrada", entrada))
				.addOrder(Order.asc("entradaItemId"))
				.list();
		
		return lista;
		
	}

}
