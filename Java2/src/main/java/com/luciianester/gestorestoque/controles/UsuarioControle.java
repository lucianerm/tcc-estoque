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
import com.luciianester.gestorestoque.entidades.Usuario;
import com.luciianester.gestorestoque.enums.Situacao;
import com.luciianester.gestorestoque.recursos.perfil.PerfilRecurso;
import com.luciianester.gestorestoque.recursos.usuario.UsuarioRecurso;

@Controller
@RequestMapping("/usuario")
public class UsuarioControle extends ControleGenerico<Usuario>{

	public UsuarioControle() {
		super("usuario");
	}

	@Override
	public RecursoGenerico<Usuario> novoRecurso() {
		return new UsuarioRecurso(new DAO());
	}
	
	@SuppressWarnings("resource")
	private void setListas(RecursoGenerico<Usuario> recurso) throws Exception {
		
		List<Situacao> situacoes = new ArrayList<Situacao>(Arrays.asList(Situacao.values()));
		this.addAtributo("situacoes", situacoes);
		
		List<Perfil> listaPerfil = (List<Perfil>) new PerfilRecurso(recurso.getDao()).listarTodos();
		this.addAtributo("listaPerfil", listaPerfil);
		
	}

	
	@Override
	public void cadastrar(RecursoGenerico<Usuario> recurso) throws Exception {
		super.cadastrar(recurso);
		this.setListas(recurso);
	}
	
	@Override
	public void editar(RecursoGenerico<Usuario> recurso, Long id) throws Exception {
		super.editar(recurso, id);
		this.setListas(recurso);
	}
	
}
