package com.luciianester.gestorestoque.controles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.enums.PessoaTipo;
import com.luciianester.gestorestoque.recursos.pessoa.PessoaRecurso;

@Controller
@RequestMapping("/pessoa")
public class PessoaControle extends ControleGenerico<Pessoa> {

	public PessoaControle() {
		super("pessoa");
	}

	@Override
	public RecursoGenerico<Pessoa> novoRecurso() {
		return new PessoaRecurso(new DAO());
	}

	private void setTipos() {
		List<PessoaTipo> tipos = new ArrayList<PessoaTipo>(Arrays.asList(PessoaTipo.values()));
		this.addAtributo("tipos", tipos);
	}
	
	@Override
	public void cadastrar(RecursoGenerico<Pessoa> recurso) throws Exception {
		super.cadastrar(recurso);
		this.setTipos();
	}
	
	@Override
	public void editar(RecursoGenerico<Pessoa> recurso, Long id) throws Exception {
		super.editar(recurso, id);
		this.setTipos();
	}
	
}
