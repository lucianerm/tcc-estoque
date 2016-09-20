package com.luciianester.gestorestoque.recursos.perfil;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Perfil;
import com.luciianester.gestorestoque.entidades.Privilegio;

public class PerfilRecurso extends RecursoGenerico<Perfil> {

	public PerfilRecurso(DAO dao) {
		super(Perfil.class, dao);
	}

	@Override
	public Long getId(Perfil objeto) {
		// TODO Auto-generated method stub
		return objeto.getPerfilId();
	}

	@Override
	public boolean validacaoGravar(Perfil objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoAlterar(Perfil objeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validacaoExcluir(Perfil objeto) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public List<Privilegio> listPrivilegiosPeloPerfil(Perfil perfil) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Privilegio> privilegios = (List<Privilegio>) this.getDao().createCriteria(Privilegio.class)
				.add(Restrictions.eq("perfil", perfil))
				.addOrder(Order.asc("privilegioId"))
				.list();
		
		return privilegios;
		
	}

}
