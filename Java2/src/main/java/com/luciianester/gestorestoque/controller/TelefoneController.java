package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Pessoa;
import com.luciianester.gestorestoque.model.Telefone;
import com.luciianester.gestorestoque.resources.pessoa.PessoaResources;
import com.luciianester.gestorestoque.resources.telefone.TelefoneResources;

@Controller
@RequestMapping("/pessoa/{paiId}/telefone")
public class TelefoneController extends ControllerCadastroFilho<Telefone> {

	private Pessoa pessoa = null;
	
	public TelefoneController() {
		super("telefone");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(ResourceGenerico<Telefone> resource) throws Exception {
		
		this.setObjeto(new Telefone());
		
		this.pessoa = new PessoaResources(resource.getDao()).listarPeloId(this.getPaiId());
		this.addAttribute("pessoa", this.pessoa);
		
		@SuppressWarnings("unchecked")
		List<Telefone> lista = (List<Telefone>) resource.getDao().createCriteria(Telefone.class)
				.add(Restrictions.eq("pessoa", this.pessoa))
				.addOrder(Order.asc("telefoneId"))
				.list();
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar(ResourceGenerico<Telefone> resource) throws Exception {
		this.pesquisar(resource);
	}

	@Override
	public void editar(ResourceGenerico<Telefone> resource, Long id) throws Exception {
		this.pesquisar(resource);
		this.setObjeto(resource.listarPeloId(id));
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(ResourceGenerico<Telefone> resource, Telefone objeto) throws Exception {
		
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
	public String excluir(ResourceGenerico<Telefone> resource, Long id) throws Exception {
		resource.remover(id);
		return "redirect:/pessoa/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public ResourceGenerico<Telefone> newResource() {
		return new TelefoneResources(new DAO());
	}

}
