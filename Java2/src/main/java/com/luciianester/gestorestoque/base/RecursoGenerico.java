package com.luciianester.gestorestoque.base;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.dao.DAO;

public abstract class RecursoGenerico<T> extends UtilidadesDoModelo implements AutoCloseable {

	private Class<T> classe;
	
	private DAO dao;

	public DAO getDao() {
		return dao;
	}
	
	public RecursoGenerico(Class<T> classe, DAO dao) {
		this.classe = classe;
		this.dao = dao;
	}
	
	public T criaNovoObjeto() throws Exception {
		return this.classe.newInstance();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodos() throws Exception {
		
		List<T> list = this.getDao().createCriteria(classe).list();
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodos(String campoPesquisa) throws Exception {
		
		List<T> list = this.getDao().createCriteria(classe).list();
		System.out.println("criar override para public List<T> listarTodos(String campoPesquisa) campo : " + campoPesquisa);
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public T listarPeloId(Long codigo) throws Exception {
		
		T objeto = (T) this.getDao().createCriteria(classe).add(Restrictions.idEq(codigo)).uniqueResult();
		return objeto;
		
	}
	
	public boolean gravar(T objeto) throws Exception {
		
		if (this.validacaoGravar(objeto)) {
			this.getDao().gravar(objeto);
			return true;
		}
		return false;
		
	}
	
	public boolean alterar(T objeto) throws Exception {
		
		if (this.validacaoAlterar(objeto)) {
			this.getDao().alterar(objeto);
			return true;
		}
		return false;
		
	}
	
	public boolean remover(T objeto) throws Exception {
		if (this.validacaoExcluir(objeto)) {
			this.getDao().remover(objeto);
			return true;
		}
		return false;
	}
	
	public boolean remover(Long codigo) throws Exception {
		
		T objeto = this.listarPeloId(codigo);
		return this.remover(objeto);
		
	}
	

	public abstract Long getId(T objeto);
	
	public boolean verificaNovoCadastro(T objeto) {
		
		Long id = this.getId(objeto);
		
		if (id==null || id.equals(0)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public abstract boolean validacaoGravar(T objeto);
	public abstract boolean validacaoAlterar(T objeto);
	public abstract boolean validacaoExcluir(T objeto);

	@Override
	public void close() throws Exception {
		this.getDao().fechar();
	}
	
	public boolean validaTexto(String nome, String valor) {
		
		if (valor==null) {
			this.setMensagemObrigatorio(nome);
			return false;
		}
		
		if (valor.equals("")) {
			this.setMensagemObrigatorio(nome);
			return false;
		}
		
		if (valor.length()<=3) {
			this.setMensagemErro(nome+" deve ser maior que três");
			return false;
		}
		return true;
		
	}
	
}
