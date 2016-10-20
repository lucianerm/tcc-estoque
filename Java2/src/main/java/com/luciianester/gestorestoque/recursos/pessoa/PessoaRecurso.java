package com.luciianester.gestorestoque.recursos.pessoa;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Endereco;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.entidades.Telefone;
import com.luciianester.gestorestoque.recursos.endereco.EnderecoRecurso;
import com.luciianester.gestorestoque.recursos.telefone.TelefoneRecurso;

public class PessoaRecurso extends RecursoGenerico<Pessoa>{

	public PessoaRecurso(DAO dao) {
		super(Pessoa.class, dao);
	}

	@Override
	public Long getId(Pessoa objeto) {
		return objeto.getPessoaId();
	}

	@Override
	public boolean validacaoGravar(Pessoa objeto) {
		
		if (objeto.getNome()==null) {
			this.setMensagemObrigatorio("Nome");
			return false;
		}
		if (objeto.getNome().length()<3) {
			this.setMensagemErro("Nome deve ter mais que 3 caracteres");
			return false;
		}
		
		if (objeto.getTipo()==null) {
			this.setMensagemObrigatorio("Tipo");
			return false;
		}
		
		if (objeto.getCpfoucnpj()==null || objeto.getCpfoucnpj().equals("")) {
			this.setMensagemObrigatorio("CPF/CNPJ");
			return false;
		}
		return true;
	}

	@Override
	public boolean validacaoAlterar(Pessoa objeto) {
		return this.validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Pessoa objeto) {
		return true;
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean remover(Pessoa pessoa) throws Exception {
		
		TelefoneRecurso telefoneRecurso = new TelefoneRecurso(this.getDao());
		List<Telefone> telefones = telefoneRecurso.listarPelaPessoa(pessoa);
		for (Telefone telefone : telefones) {
			telefoneRecurso.remover(telefone);
		}
		
		EnderecoRecurso enderecoRecurso = new EnderecoRecurso(this.getDao());
		List<Endereco> enderecos = enderecoRecurso.listarPelaPessoa(pessoa);
		for (Endereco endereco : enderecos) {
			enderecoRecurso.remover(endereco);
		}
		
		return super.remover(pessoa);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listarTodos(String campoPesquisa) throws Exception {
		
		if (campoPesquisa==null || campoPesquisa.equals("")) {
			return super.listarTodos();
		} else {
			
			List<Pessoa> lista = (List<Pessoa>) this.getDao()
					.createCriteria(Pessoa.class)
					.add(Restrictions.ilike("nome", "%"+campoPesquisa+"%"))
					.list();
			
			return lista;
			
		}
		
	}
	
}
