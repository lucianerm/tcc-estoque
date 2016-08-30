package com.luciianester.gestorestoque.core;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;

import com.luciianester.gestorestoque.core.dao.DAO;

public abstract class ResourceGenerico<T> {

	private Model model;
	
	private Class<T> classe;
	
	private DAO<T> dao = new DAO<>();

	public DAO<T> getDao() {
		return dao;
	}
	
	public ResourceGenerico(Class<T> classe) {
		this.classe = classe;
	}
	
	public T newInstance() throws Exception {
		return this.classe.newInstance();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		
		List<T> list = this.getDao().getSessao().createCriteria(classe).list();
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public T listarPeloId(Long codigo) {
		
		T objeto = (T) this.getDao().getSessao().createCriteria(classe).add(Restrictions.idEq(codigo)).uniqueResult();
		return objeto;
		
	}
	
	public boolean gravar(T objeto) {
		
		if (this.validacaoGravar(objeto)) {
			this.getDao().gravar(objeto);
			return true;
		}
		return false;
		
	}
	
	public boolean alterar(T objeto) {
		
		if (this.validacaoAlterar(objeto)) {
			this.getDao().alterar(objeto);
			return true;
		}
		return false;
		
	}
	
	public boolean remover(Long codigo) {
		
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
	
}
