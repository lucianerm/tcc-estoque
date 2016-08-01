package com.luciianester.gestorestoque.core;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.core.dao.DAO;

public class ResourceGenerico<T> {

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
	
	public void gravar(T objeto) {
		
		this.getDao().gravar(objeto);
		
	}
	
	public void alterar(T objeto) {
		
		this.getDao().alterar(objeto);
		
	}
	
	public void remover(Long codigo) {
		
		T objeto = this.listarPeloId(codigo);
		this.getDao().remover(objeto);
		
	}
	
}
