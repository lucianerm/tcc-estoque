package com.luciianester.gestorestoque.core.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class DAO<T> {

	private Session sessao;

	public DAO() {
	}

	public T gravar(T entity) {
		try {
			getSessao().beginTransaction();
			getSessao().save(entity);
			getSessao().getTransaction().commit();
			getSessao().close();
			return entity;
		} catch (Exception e) {
			getSessao().getTransaction().rollback();
			getSessao().close();
			return null;
		}
	}

	public T alterar(T entity) {
		try {
			getSessao().beginTransaction();
			getSessao().merge(entity);
			getSessao().getTransaction().commit();
			getSessao().close();
			return entity;
		} catch (Exception e) {
			getSessao().getTransaction().rollback();
			getSessao().close();
			return null;
		}
	}

	public void remover(T entity) {
		try {
			getSessao().beginTransaction();
			getSessao().delete(entity);
			getSessao().getTransaction().commit();
		} catch (Exception e) {
			getSessao().getTransaction().rollback();
		} finally {
			getSessao().close();
		}
	}
	
	public Session getSessao() {
		if (sessao == null || !sessao.isOpen()) {
			sessao = HibernateUtil.getSessionFactory().openSession();
		}
		return sessao;
	}

}