package com.luciianester.gestorestoque.controles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Marca;
import com.luciianester.gestorestoque.recursos.marca.MarcaRecurso;

@Controller
@RequestMapping("/marca")
public class MarcaControle extends ControleGenerico<Marca>{

	public MarcaControle() {
		super("marca");
	}

	@Override
	public RecursoGenerico<Marca> novoRecurso() {
		return new MarcaRecurso(new DAO());
	}

}
