package com.luciianester.gestorestoque.core;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;

import com.luciianester.gestorestoque.core.dao.DAO;

public abstract class ResourceGenerico<T> implements AutoCloseable {

	private Model model;
	
	private Class<T> classe;
	
	private DAO dao;

	public DAO getDao() {
		return dao;
	}
	
	public ResourceGenerico(Class<T> classe, DAO dao) {
		this.classe = classe;
		this.dao = dao;
	}
	
	public T newInstance() throws Exception {
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
	
	public boolean ehNovo(T objeto) {
		
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	@Override
	public void close() throws Exception {
		this.getDao().close();
	}
	
}
