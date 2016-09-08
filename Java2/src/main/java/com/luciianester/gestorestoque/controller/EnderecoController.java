package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Endereco;
import com.luciianester.gestorestoque.model.Pessoa;
import com.luciianester.gestorestoque.resources.endereco.EnderecoResources;
import com.luciianester.gestorestoque.resources.pessoa.PessoaResources;

@Controller
@RequestMapping("/pessoa/{paiId}/endereco")
public class EnderecoController extends ControllerCadastroFilho<Endereco> {

	private Pessoa pessoa = null;
	
	public EnderecoController() {
		super("endereco");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(ResourceGenerico<Endereco> resource) throws Exception {
		
		this.setObjeto(new Endereco());
		
		this.pessoa = new PessoaResources(resource.getDao()).listarPeloId(this.getPaiId());
		this.addAttribute("pessoa", this.pessoa);
		
		@SuppressWarnings("unchecked")
		List<Endereco> lista = (List<Endereco>) resource.getDao().createCriteria(Endereco.class)
				.add(Restrictions.eq("pessoa", this.pessoa))
				.addOrder(Order.asc("enderecoId"))
				.list();
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar(ResourceGenerico<Endereco> resource) throws Exception {
		this.pesquisar(resource);
	}

	@Override
	public void editar(ResourceGenerico<Endereco> resource, Long id) throws Exception {
		this.pesquisar(resource);
		this.setObjeto(resource.listarPeloId(id));
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(ResourceGenerico<Endereco> resource, Endereco objeto) throws Exception {
		
		this.pessoa = new PessoaResources(resource.getDao()).listarPeloId(this.getPaiId());
		
		objeto.setPessoa(this.pessoa);
		
		if (resource.ehNovo(objeto)) {
			resource.gravar(objeto);
		} else {
			resource.alterar(objeto);
		}
		
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(ResourceGenerico<Endereco> resource, Long id) throws Exception {
		resource.remover(id);
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public ResourceGenerico<Endereco> newResource() {
		return new EnderecoResources(new DAO());
	}

}
