package com.luciianester.gestorestoque.recursos.telefone;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.entidades.Telefone;

public class TelefoneRecurso extends RecursoGenerico<Telefone> {

	public TelefoneRecurso(DAO dao) {
		super(Telefone.class, dao);
	}

	@Override
	public Long getId(Telefone objeto) {
		return objeto.getTelefoneId();
	}

	@Override
	public boolean validacaoGravar(Telefone objeto) {
		
		if (objeto.getDescricao()==null || objeto.getDescricao().length()<3) {
			this.setMensagemObrigatorio("Descrição");
			return false;
		}
		
		if (objeto.getNumero()==null || objeto.getNumero().length()<3) {
			this.setMensagemObrigatorio("Número");
			return false;
		}
		
		if (objeto.getNumero().length()!=12) {
			this.setMensagemErro("Número Inválido");
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean validacaoAlterar(Telefone objeto) {
		return this.validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Telefone objeto) {
		return true;
	}
	
	public List<Telefone> listarPelaPessoa(Pessoa pessoa) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Telefone> lista = (List<Telefone>) this.getDao().createCriteria(Telefone.class)
			.add(Restrictions.eq("pessoa", pessoa))
			.list();
		
		return lista;
		
	}

}
