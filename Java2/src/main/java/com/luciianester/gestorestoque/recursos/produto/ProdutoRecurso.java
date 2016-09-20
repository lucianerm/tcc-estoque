package com.luciianester.gestorestoque.recursos.produto;

import com.luciianester.gestorestoque.base.ModelUtils;
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
		ModelUtils modelUtils = new ModelUtils(this.getModelo());
		if(objeto.getDescricao() == null || objeto.getDescricao().equals("") ){
			modelUtils.setMensagemErro("Campo descrição é obrigatório.");
			return false;
		}else if (objeto.getDescricao().length() <3){
				modelUtils.setMensagemErro("Campo descrição deve ser maior que três caracteres.");
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
	
}
