package com.luciianester.gestorestoque.controles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;
import com.luciianester.gestorestoque.recursos.entrada.EntradaRecurso;

@Controller
@RequestMapping("/entrada")
public class EntradaControle extends ControleGenerico<Entrada>{

	public EntradaControle() {
		super("entrada");
	}

	@Override
	public RecursoGenerico<Entrada> novoRecurso() {
		return new EntradaRecurso(new DAO());
	}

}
