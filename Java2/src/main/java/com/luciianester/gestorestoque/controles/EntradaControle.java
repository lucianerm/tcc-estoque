package com.luciianester.gestorestoque.controles;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleGenerico;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.recursos.entrada.EntradaRecurso;
import com.luciianester.gestorestoque.recursos.pessoa.PessoaRecurso;

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
	
	@Override
	public void pesquisar(RecursoGenerico<Entrada> recurso) throws Exception {
		
		@SuppressWarnings("resource")
		List<Pessoa> listaFornecedores = new PessoaRecurso(recurso.getDao()).listarTodos();
		this.addAtributo("listaFornecedores", listaFornecedores);
		
		super.pesquisar(recurso);
	}
	
	@Override
	public void editar(RecursoGenerico<Entrada> recurso, Long id) throws Exception {
		super.editar(recurso, id);
		this.addAtributo("totalEntrada", ((EntradaRecurso)recurso).calcularTotal(id));
	}

	@RequestMapping("/novofornecedor/{entradaId}")
	public String novaCategoria(@PathVariable("entradaId") Long entradaId, HttpSession sessao) {
		
		sessao.setAttribute("voltarTelaEntrada", "pessoa");
		sessao.setAttribute("pessoaEntradaId", entradaId);
		
		return "redirect:/pessoa/cadastro";
		
	}
	
}
