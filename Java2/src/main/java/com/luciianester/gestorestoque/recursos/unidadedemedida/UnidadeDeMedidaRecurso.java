package com.luciianester.gestorestoque.recursos.unidadedemedida;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;

public class UnidadeDeMedidaRecurso extends RecursoGenerico<UnidadeDeMedida> {

	public UnidadeDeMedidaRecurso(DAO dao) {
		super(UnidadeDeMedida.class, dao);
	}

	@Override
	public Long getId(UnidadeDeMedida objeto) {
		return objeto.getUnidadeDeMedidaId();
	}
	

	@Override
	public boolean validacaoGravar(UnidadeDeMedida objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(UnidadeDeMedida objeto) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean validacaoExcluir(UnidadeDeMedida objeto) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public UnidadeDeMedidaDoProduto listarPeloProduto(Long id) throws Exception {
		
		UnidadeDeMedidaDoProduto unidadeDeMedidaDoProduto = new UnidadeDeMedidaDoProduto();
		
		@SuppressWarnings("resource")
		Produto produto = new ProdutoRecurso(this.getDao()).listarPeloId(id);
		
		@SuppressWarnings("unchecked")
		List<UnidadeDeMedida> lista = this.getDao().createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", produto))
				.addOrder(Order.asc("quantidade"))
				.list();
		
		unidadeDeMedidaDoProduto.setLista(lista);
		
		return unidadeDeMedidaDoProduto;
		 	
	}
	
	public UnidadeDeMedidaDoProduto listarPeloItemDaEntrada(Long id) throws Exception {
		
		@SuppressWarnings("resource")
		EntradaItem entradaItem = new EntradaItemRecurso(this.getDao()).listarPeloId(id);
		
		return this.listarPeloProduto(entradaItem.getProduto().getProdutoId());
		
	}

}
