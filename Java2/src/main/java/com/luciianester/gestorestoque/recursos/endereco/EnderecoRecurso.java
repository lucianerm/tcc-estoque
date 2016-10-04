package com.luciianester.gestorestoque.recursos.endereco;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Endereco;
import com.luciianester.gestorestoque.entidades.Pessoa;

public class EnderecoRecurso extends RecursoGenerico<Endereco> {

	public EnderecoRecurso(DAO dao) {
		super(Endereco.class, dao);
	}

	@Override
	public Long getId(Endereco objeto) {
		return objeto.getEnderecoId();
	}

	@Override
	public boolean validacaoGravar(Endereco objeto) {
		
		if (objeto.getCep()==null || objeto.getCep().length()<3) {
			this.setMensagemObrigatorio("CEP");
			return false;
		}
		
		if (objeto.getEstado()==null || objeto.getEstado().length()<3) {
			this.setMensagemObrigatorio("Estado");
			return false;
		}
		
		if (objeto.getMunicipio()==null || objeto.getMunicipio().length()<3) {
			this.setMensagemObrigatorio("Município");
			return false;
		}

		if (objeto.getBairro()==null || objeto.getBairro().length()<3) {
			this.setMensagemObrigatorio("Bairro");
			return false;
		}

		if (objeto.getRua()==null || objeto.getRua().length()<3) {
			this.setMensagemObrigatorio("Rua");
			return false;
		}

		if (objeto.getNumero()==null || objeto.getNumero().length()<3) {
			this.setMensagemObrigatorio("Número");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Endereco objeto) {
		return this.validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Endereco objeto) {
		return true;
	}

	public List<Endereco> listarPelaPessoa(Pessoa pessoa) throws Exception {

		@SuppressWarnings("unchecked")
		List<Endereco> lista = (List<Endereco>) this.getDao().createCriteria(Endereco.class)
			.add(Restrictions.eq("pessoa", pessoa))
			.list();
		
		return lista;
		
	}

}
