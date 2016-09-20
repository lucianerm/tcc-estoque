package com.luciianester.gestorestoque.controles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Perfil;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.recursos.perfil.PerfilRecurso;

@Controller
@RequestMapping("/perfil")
public class PerfilController extends ControleGenerico<Perfil>{

	public PerfilController() {
		super("perfil");
	}
	
	@Override
	public void cadastrar(RecursoGenerico<Perfil> recurso) throws Exception {
		super.cadastrar(recurso);

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		this.addAtributo("tipos", tipos);
		
	}
	
	@Override
	public void editar(RecursoGenerico<Perfil> recurso, Long id) throws Exception {
		super.editar(recurso, id);

		List<PerfilTipo> tipos = new ArrayList<PerfilTipo>(Arrays.asList(PerfilTipo.values()));
		this.addAtributo("tipos", tipos);
		
	}

	@Override
	public RecursoGenerico<Perfil> novoRecurso() {
		return new PerfilRecurso(new DAO());
	}
	
}
