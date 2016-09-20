package com.luciianester.gestorestoque.controles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Saida;
import com.luciianester.gestorestoque.recursos.saida.SaidaRecurso;

@Controller
@RequestMapping("/saida")
public class SaidaControle extends ControleGenerico<Saida>{

	public SaidaControle() {
		super("saida");
	}

	@Override
	public RecursoGenerico<Saida> novoRecurso() {
		return new SaidaRecurso(new DAO());
	}

}
