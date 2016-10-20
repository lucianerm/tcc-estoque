package com.luciianester.gestorestoque.recursos.produto;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Produto;

public class ProdutoRecurso extends RecursoGenerico<Produto> {

	public ProdutoRecurso(DAO dao) {
		super(Produto.class, dao);
	}

	@Override
	public Long getId(Produto objeto) {
		
		return objeto.getProdutoId();
	}

	@Override
	public boolean validacaoGravar(Produto objeto) {
		if(objeto.getDescricao() == null || objeto.getDescricao().equals("") ){
			this.setMensagemErro("Campo descrição é obrigatório.");
			return false;
		}else if (objeto.getDescricao().length() <3){
				this.setMensagemErro("Campo descrição deve ser maior que três caracteres.");
				return false;
			}
					
		return true;
	}

	@Override
	public boolean validacaoAlterar(Produto objeto) {
		
		return validacaoGravar(objeto);
	}


	@Override
	public boolean validacaoExcluir(Produto objeto) {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarTodos(String campoPesquisa) throws Exception {
		
		if (campoPesquisa==null || campoPesquisa.equals("")) {
			return super.listarTodos();
		} else {
			
			List<Produto> lista = (List<Produto>) this.getDao()
					.createCriteria(Produto.class)
					.add(Restrictions.ilike("descricao", "%"+campoPesquisa+"%"))
					.list();
			
			return lista;
			
		}
		
	}
	
}
