package com.luciianester.gestorestoque.controles;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Marca;
import com.luciianester.gestorestoque.recursos.marca.MarcaRecurso;

@Controller
@RequestMapping("/marca")
public class MarcaControle extends ControleGenerico<Marca> {

	public MarcaControle() {
		super("marca");
	}

	@Override
	public RecursoGenerico<Marca> novoRecurso() {
		return new MarcaRecurso(new DAO());
	}

	@RequestMapping("/gravar2")
	public String gravar2(@ModelAttribute("objeto") Marca objeto, Model modelo, HttpSession sessao) throws Exception {
		
		String redirecionar = "1";
		
		this.setModelo(modelo);

		try (RecursoGenerico<Marca> recurso = this.criaRecurso();) {
			
			this.pesquisar(recurso);
			redirecionar = salvar(recurso, objeto);
			
		} catch (Exception e) {
			throw e;
		}
		
		if (redirecionar.indexOf("redirect")==0) {
		
			try {
				if ("marca".equals(sessao.getAttribute("voltarTelaProduto"))) {
					Long produtoId = Long.valueOf(""+sessao.getAttribute("marcaProdutoId"));
					
					redirecionar = "redirect:/produto/cadastro";
					if (produtoId>0) {
						redirecionar = "redirect:/produto/"+produtoId;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			sessao.setAttribute("voltarTelaProduto", null);
			
		}
		
		return redirecionar;
		
	}
	
}
