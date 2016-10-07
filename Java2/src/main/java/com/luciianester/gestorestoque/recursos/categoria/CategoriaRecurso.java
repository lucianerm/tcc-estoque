package com.luciianester.gestorestoque.recursos.categoria;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Categoria;
import com.luciianester.gestorestoque.entidades.Produto;

public class CategoriaRecurso extends RecursoGenerico<Categoria> {

	public CategoriaRecurso(DAO dao) {
		super(Categoria.class, dao);
	}

	@Override
	public Long getId(Categoria objeto) {
		
		return objeto.getCategoriaId();
	}

	@Override
	public boolean validacaoGravar(Categoria objeto) {

		if (!validaTexto("Descrição", objeto.getDescricao())) {
			return false;
		}
		return true;
		
	}

	@Override
	public boolean validacaoAlterar(Categoria objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Categoria objeto) {
		
		try {
			
			@SuppressWarnings("unchecked")
			List<Produto> lista = (List<Produto>) this.getDao().createCriteria(Produto.class)
					.add(Restrictions.eq("categoria", objeto))
					.list();
			
			if (lista!=null && lista.size()>0) {
				
				String mensagem = "Esta Categoria tem vínculo com os Produtos:";
				
				for (Produto produto : lista) {
					mensagem += "<br/>" + produto.getProdutoId() + " - " + produto.getDescricao();
				}
				
				this.setMensagemErro(mensagem);
				return false;
				
			}
			
		} catch (Exception e) {
			this.setMensagemErro("Erro ao validar Produtos");
			return false;
		}
		
		return true;
	}

}
