package com.luciianester.gestorestoque.recursos.saida.item;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Saida;
import com.luciianester.gestorestoque.entidades.SaidaItem;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaRecurso;

public class SaidaItemRecurso extends RecursoGenerico<SaidaItem> {

	public SaidaItemRecurso(DAO dao) {
		super(SaidaItem.class, dao);
	}

	@Override
	public Long getId(SaidaItem objeto) {
		return objeto.getSaidaItemId();
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean validacaoGravar(SaidaItem objeto) {
		
		try {
			
			if (objeto.getEntradaItem()==null || objeto.getEntradaItem().getEntradaItemId()==null || objeto.getEntradaItem().getEntradaItemId().equals(0l)) {
				this.setMensagemObrigatorio("Entrada");
				return false;
			}
			
			if (objeto.getUnidadeDeMedida()==null || objeto.getUnidadeDeMedida().getUnidadeDeMedidaId()==null || objeto.getUnidadeDeMedida().getUnidadeDeMedidaId().equals(0l)) {
				this.setMensagemObrigatorio("Unidade de Medida");
				return false;
			}
			
			if (objeto.getQuantidade()==null || objeto.getQuantidade().equals(BigDecimal.ZERO)) {
				this.setMensagemObrigatorio("Quantidade");
				return false;
			}
			
			EntradaItemRecurso entradaItemRecurso = new EntradaItemRecurso(this.getDao());
			EntradaItem entradaItem = entradaItemRecurso.listarPeloId(objeto.getEntradaItem().getEntradaItemId());
			
			entradaItemRecurso.calculaSaldo(entradaItem);
			
			UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedidaRecurso(this.getDao()).listarPeloId(objeto.getUnidadeDeMedida().getUnidadeDeMedidaId());
			objeto.setValor(unidadeDeMedida.getValorDeVenda());
			
			
			BigDecimal quantidade = objeto.getQuantidade().multiply(BigDecimal.valueOf(unidadeDeMedida.getQuantidade()));
			
			if (entradaItem.getSaldo().compareTo(quantidade)<0) {
				this.setMensagemErro("Saldo Insuficiente");
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.setMensagemErro(e.getMessage());
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean validacaoAlterar(SaidaItem objeto) {
		return this.validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(SaidaItem objeto) {
		return true;
	}

	public List<SaidaItem> listarPelaEntrada(EntradaItem entradaItem) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<SaidaItem> list = this.getDao()
				.createCriteria(SaidaItem.class)
				.add(Restrictions.eq("entradaItem", entradaItem))
				.list();
		
		return list;
		
	}
	
	@Override
	public List<SaidaItem> listarTodos() throws Exception {
		List<SaidaItem> listarTodos = super.listarTodos();
		for (SaidaItem saidaItem : listarTodos) {
			saidaItem.setProdutoId(saidaItem.getEntradaItem().getProduto().getProdutoId());
		}
		return listarTodos;
	}
	
	@Override
	public SaidaItem listarPeloId(Long codigo) throws Exception {
		SaidaItem saidaItem = super.listarPeloId(codigo);
		saidaItem.setProdutoId(saidaItem.getEntradaItem().getProduto().getProdutoId());
		return saidaItem;
	}
	
	public List<SaidaItem> listarPelaSaida(Saida saida) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<SaidaItem> lista = (List<SaidaItem>) this.getDao().createCriteria(SaidaItem.class)
				.add(Restrictions.eq("saida", saida))
				.addOrder(Order.asc("saidaItemId"))
				.list();
		
		return lista;
		
	}

}
