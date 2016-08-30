package com.luciianester.gestorestoque.resources.produto;

import com.luciianester.gestorestoque.core.ModelUtils;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Produto;

public class ProdutoResources extends ResourceGenerico<Produto> {

	public ProdutoResources() {
		super(Produto.class);
	}

	@Override
	public Long getId(Produto objeto) {
		
		return objeto.getProdutoId();
	}

	@Override
	public boolean validacaoGravar(Produto objeto) {
		ModelUtils modelUtils = new ModelUtils(this.getModel());
		if(objeto.getDescricao() == null || objeto.getDescricao().equals("") ){
			modelUtils.setMensagemErro("Campo descri��o � obrigat�rio.");
			return false;
		}else if (objeto.getDescricao().length() <3){
				modelUtils.setMensagemErro("Campo descri��o deve ser maior que tr�s caracteres.");
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
