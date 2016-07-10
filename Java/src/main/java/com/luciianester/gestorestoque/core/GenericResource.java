package com.luciianester.gestorestoque.core;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.dao.DAO;

public class GenericResource<T> {

	private Class<T> classe;
	
	private DAO<T> dao = new DAO<>();

	public DAO<T> getDao() {
		return dao;
	}
	
	public GenericResource(Class<T> classe) {
		this.classe = classe;
	}
	
	public T newInstance() throws Exception {
		return this.classe.newInstance();
	}
	
	public List<T> listAll() {
		
		List<T> list = this.getDao().getSession().createCriteria(classe).list();
		return list;
		
	}
	
	public T listById(Long codigo) {
		
		T objeto = (T) this.getDao().getSession().createCriteria(classe).add(Restrictions.idEq(codigo)).uniqueResult();
		return objeto;
		
	}
	
	public void salvar(T objeto) {
		
		this.getDao().save(objeto);
		
	}
	
	public void alterar(T objeto) {
		
		this.getDao().update(objeto);
		
	}
	
	public void deletar(Long codigo) {
		
		T objeto = this.listById(codigo);
		this.getDao().delete(objeto);
		
	}
	
}
