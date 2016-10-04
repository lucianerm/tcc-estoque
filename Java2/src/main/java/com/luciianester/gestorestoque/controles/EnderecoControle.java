package com.luciianester.gestorestoque.controles;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleCadastroFilho;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Endereco;
import com.luciianester.gestorestoque.entidades.Pessoa;
import com.luciianester.gestorestoque.recursos.endereco.EnderecoRecurso;
import com.luciianester.gestorestoque.recursos.pessoa.PessoaRecurso;

@Controller
@RequestMapping("/pessoa/{paiId}/endereco")
public class EnderecoControle extends ControleCadastroFilho<Endereco> {

	private Pessoa pessoa = null;
	
	public EnderecoControle() {
		super("endereco");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(RecursoGenerico<Endereco> recurso) throws Exception {
		
		this.setObjeto(new Endereco());
		
		this.pessoa = new PessoaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		this.addAtributo("pessoa", this.pessoa);
		
		@SuppressWarnings("unchecked")
		List<Endereco> lista = (List<Endereco>) recurso.getDao().createCriteria(Endereco.class)
				.add(Restrictions.eq("pessoa", this.pessoa))
				.addOrder(Order.asc("enderecoId"))
				.list();
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar(RecursoGenerico<Endereco> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@Override
	public void editar(RecursoGenerico<Endereco> recurso, Long id) throws Exception {
		this.pesquisar(recurso);
		this.setObjeto(recurso.listarPeloId(id));
	}

	@Override
	public String salvar(RecursoGenerico<Endereco> recurso, Endereco objeto) throws Exception {
		
		this.pesquisar(recurso);
		
		objeto.setPessoa(this.pessoa);
		this.setObjeto(objeto);
		
		if (!this.salvarOuAlterar(recurso, objeto)) {
			return this.getCaminho()+"/cadastro";
		}
		
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(RecursoGenerico<Endereco> recurso, Long id) throws Exception {
		recurso.remover(id);
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public RecursoGenerico<Endereco> novoRecurso() {
		return new EnderecoRecurso(new DAO());
	}

}
