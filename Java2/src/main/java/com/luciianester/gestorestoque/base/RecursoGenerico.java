package com.luciianester.gestorestoque.base;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;

import com.luciianester.gestorestoque.base.dao.DAO;

public abstract class RecursoGenerico<T> implements AutoCloseable {

	private Model modelo;
	
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
	
	public boolean remover(Long codigo) throws Exception {
		
		T objeto = this.listarPeloId(codigo);
		if (this.validacaoExcluir(objeto)) {
			this.getDao().remover(objeto);
			return true;
		}
		return false;
		
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

	public Model getModelo() {
		return modelo;
	}

	public void setModelo(Model model) {
		this.modelo = model;
	}
	
	@Override
	public void close() throws Exception {
		this.getDao().fechar();
	}
	
}