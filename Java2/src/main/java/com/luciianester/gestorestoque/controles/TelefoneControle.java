package com.luciianester.gestorestoque.controles;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleCadastroFilho;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.entidades.Telefone;
import com.luciianester.gestorestoque.recursos.pessoa.PessoaRecurso;
import com.luciianester.gestorestoque.recursos.telefone.TelefoneRecurso;

@Controller
@RequestMapping("/pessoa/{paiId}/telefone")
public class TelefoneControle extends ControleCadastroFilho<Telefone> {

	private Pessoa pessoa = null;
	
	public TelefoneControle() {
		super("telefone");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(RecursoGenerico<Telefone> recurso) throws Exception {
		
		this.setObjeto(new Telefone());
		
		this.pessoa = new PessoaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		this.addAtributo("pessoa", this.pessoa);
		
		@SuppressWarnings("unchecked")
		List<Telefone> lista = (List<Telefone>) recurso.getDao().createCriteria(Telefone.class)
				.add(Restrictions.eq("pessoa", this.pessoa))
				.addOrder(Order.asc("telefoneId"))
				.list();
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar(RecursoGenerico<Telefone> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@Override
	public void editar(RecursoGenerico<Telefone> recurso, Long id) throws Exception {
		this.pesquisar(recurso);
		this.setObjeto(recurso.listarPeloId(id));
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(RecursoGenerico<Telefone> recurso, Telefone objeto) throws Exception {
		
		this.pessoa = new PessoaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		
		objeto.setPessoa(this.pessoa);
		
		if (recurso.verificaNovoCadastro(objeto)) {
			recurso.gravar(objeto);
		} else {
			recurso.alterar(objeto);
		}
		
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(RecursoGenerico<Telefone> recurso, Long id) throws Exception {
		recurso.remover(id);
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public RecursoGenerico<Telefone> novoRecurso() {
		return new TelefoneRecurso(new DAO());
	}

}
