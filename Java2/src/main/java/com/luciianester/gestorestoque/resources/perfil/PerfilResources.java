package com.luciianester.gestorestoque.resources.perfil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.Tela;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.model.Privilegio;

public class PerfilResources extends ResourceGenerico<Perfil> {

	public PerfilResources(DAO dao) {
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
	
	public List<Privilegio> listPrivilegiosByPerfil(Perfil perfil) throws Exception {
		
		List<Tela> lista = new ArrayList<>();

		@SuppressWarnings("unchecked")
		List<Privilegio> privilegios = (List<Privilegio>) this.getDao().createCriteria(Privilegio.class)
				.add(Restrictions.eq("perfil", perfil))
				.addOrder(Order.asc("privilegioId"))
				.list();
		
		return privilegios;
		
	}

}
