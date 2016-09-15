package com.luciianester.gestorestoque.resources.privilegio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.model.Privilegio;

public class PrivilegioResources extends ResourceGenerico<Privilegio> {

	public PrivilegioResources(DAO dao) {
		super(Privilegio.class, dao);
	}

	@Override
	public Long getId(Privilegio objeto) {
		return objeto.getPrivilegioId();
	}

	@Override
	public boolean validacaoGravar(Privilegio objeto) {
		return true;
	}

	@Override
	public boolean validacaoAlterar(Privilegio objeto) {
		return true;
	}

	@Override
	public boolean validacaoExcluir(Privilegio objeto) {
		return true;
	}

}
