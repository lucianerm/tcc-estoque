package com.luciianester.gestorestoque.recursos.entrada;

import java.util.List;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;

public class EntradaRecurso extends RecursoGenerico<Entrada> {

	public EntradaRecurso(DAO dao) {
		super(Entrada.class, dao);
	}

	@Override
	public Long getId(Entrada objeto) {
		
		return objeto.getEntradaId();
	}

	@Override
	public boolean validacaoGravar(Entrada objeto) {

		if (objeto.getData()==null) {
			this.setMensagemObrigatorio("Data");
			return false;
		}
		
		if (objeto.getFornecedor()!=null) {
			if (objeto.getFornecedor().getPessoaId()==null || objeto.getFornecedor().getPessoaId().equals(0l)) {
				objeto.setFornecedor(null);
			}
		}
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Entrada objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Entrada objeto) {
		return true;
	}
	
	@Override
	public boolean remover(Entrada entrada) throws Exception {
		
		@SuppressWarnings("resource")
		EntradaItemRecurso entradaItemRecurso = new EntradaItemRecurso(this.getDao());
		List<EntradaItem> itens = entradaItemRecurso.listarPelaEntrada(entrada);
		for (EntradaItem entradaItem : itens) {
			entradaItemRecurso.remover(entradaItem);
		}
		
		return super.remover(entrada);
		
	}

}
