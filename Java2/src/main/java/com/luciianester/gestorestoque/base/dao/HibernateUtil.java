package com.luciianester.gestorestoque.base.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes;

	public static SessionFactory getFabricaDeSessoes() {
		if (fabricaDeSessoes == null) {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			fabricaDeSessoes = configuration.buildSessionFactory(builder.build());
		}
		return fabricaDeSessoes;
	}

}