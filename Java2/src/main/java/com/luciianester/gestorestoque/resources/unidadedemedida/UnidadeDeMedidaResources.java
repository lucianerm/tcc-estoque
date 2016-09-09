package com.luciianester.gestorestoque.resources.unidadedemedida;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.EntradaItem;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;
import com.luciianester.gestorestoque.resources.entrada.item.EntradaItemResources;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;

public class UnidadeDeMedidaResources extends ResourceGenerico<UnidadeDeMedida> {

	public UnidadeDeMedidaResources(DAO dao) {
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
		Produto produto = new ProdutoResources(this.getDao()).listarPeloId(id);
		
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
		EntradaItem entradaItem = new EntradaItemResources(this.getDao()).listarPeloId(id);
		
		return this.listarPeloProduto(entradaItem.getProduto().getProdutoId());
		
	}

}
