package com.luciianester.gestorestoque.recursos.entrada.item;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;

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


	public EntradaItemDoProduto listarPeloProduto(Long id) {
		
		EntradaItemDoProduto doProduto = new EntradaItemDoProduto();
		
		try {

			@SuppressWarnings("resource")
			Produto produto = new ProdutoRecurso(this.getDao()).listarPeloId(id);
			
			@SuppressWarnings("unchecked")
			List<EntradaItem> listaEntradaItem = (List<EntradaItem>) this.getDao().createCriteria(EntradaItem.class)
				.add(Restrictions.eq("produto", produto))
				.list();
			
			doProduto.setLista(listaEntradaItem);
			
		} catch (Exception e) {
			
		}
		
		
		return doProduto;
	}

}
