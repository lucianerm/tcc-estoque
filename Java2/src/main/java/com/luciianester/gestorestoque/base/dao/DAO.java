package com.luciianester.gestorestoque.base.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class DAO {

	private Session sessao;

	public DAO() {
	}

	private void abrir() throws Exception {
		this.fechar();
		if (sessao == null) {
			sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			sessao.beginTransaction();
		}
	}
	
	private Session getSessao() throws Exception {
		this.abrir();
		return sessao;
	}
	
	public Object gravar(Object entity) throws Exception {
		
		this.getSessao().save(entity);
		return entity;
		
	}

	public Object alterar(Object entity) throws Exception {
		
		this.getSessao().merge(entity);
		return entity;
		
	}

	public void remover(Object entity) throws Exception {
		
		this.getSessao().delete(entity);
		
	}
	
	public Criteria createCriteria(Class<?> persistentClass) throws Exception {
		
		Criteria criteria = this.getSessao().createCriteria(persistentClass);
		return criteria;
		
	}
	
	public void rollback() throws Exception {
		
		sessao.getTransaction().rollback();
		sessao.close();
		sessao = null;
		
	}
	
	public void commit() throws Exception {
		
		sessao.getTransaction().commit();
		sessao.close();
		sessao = null;
		
	}

	public void fechar() throws Exception {
		
		if (sessao != null) {
			
			try {
				
				this.commit();
				
			} catch (Exception e) {
				
				try {
					this.rollback();
				} catch (Exception e2) {}
				try {
					sessao.close();
				} catch (Exception e2) {}
				
				throw e;
				
			}
			
		}
		
	}
	
}