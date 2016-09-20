package com.luciianester.gestorestoque.controles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Categoria;
import com.luciianester.gestorestoque.recursos.categoria.CategoriaRecurso;

@Controller
@RequestMapping("/categoria")
public class CategoriaControle extends ControleGenerico<Categoria>{

	public CategoriaControle() {
		super("categoria");
	}

	@Override
	public RecursoGenerico<Categoria> novoRecurso() {
		return new CategoriaRecurso(new DAO());
	}

}
