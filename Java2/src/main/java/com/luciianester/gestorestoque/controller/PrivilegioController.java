package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.Tela;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.model.Privilegio;
import com.luciianester.gestorestoque.resources.perfil.PerfilResources;
import com.luciianester.gestorestoque.resources.privilegio.PrivilegioResources;

@Controller
@RequestMapping("/perfil/{paiId}/privilegio")
public class PrivilegioController extends ControllerCadastroFilho<Privilegio> {

	private Perfil perfil = null;
	
	public PrivilegioController() {
		super("privilegio");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(ResourceGenerico<Privilegio> resource) throws Exception {
		
		this.setObjeto(new Privilegio());
		
		this.perfil = new PerfilResources(resource.getDao()).listarPeloId(this.getPaiId());
		this.addAttribute("perfil", this.perfil);
		
		@SuppressWarnings("unchecked")
		List<Privilegio> lista = (List<Privilegio>) resource.getDao().createCriteria(Privilegio.class)
				.add(Restrictions.eq("perfil", this.perfil))
				.addOrder(Order.asc("privilegioId"))
				.list();
		
		this.setLista(lista);
		
		List<Tela> telas = new ArrayList<Tela>(Arrays.asList(Tela.values()));
		this.addAttribute("telas", telas);
		
	}

	@Override
	public void cadastrar(ResourceGenerico<Privilegio> resource) throws Exception {
		this.pesquisar(resource);
	}

	@Override
	public void editar(ResourceGenerico<Privilegio> resource, Long id) throws Exception {
		this.pesquisar(resource);
		this.setObjeto(resource.listarPeloId(id));
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(ResourceGenerico<Privilegio> resource, Privilegio objeto) throws Exception {
		
		this.perfil = new PerfilResources(resource.getDao()).listarPeloId(this.getPaiId());
		
		objeto.setPerfil(this.perfil);
		
		if (resource.ehNovo(objeto)) {
			resource.gravar(objeto);
		} else {
			resource.alterar(objeto);
		}
		
		return "redirect:/perfil/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(ResourceGenerico<Privilegio> resource, Long id) throws Exception {
		resource.remover(id);
		return "redirect:/perfil/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public ResourceGenerico<Privilegio> newResource() {
		return new PrivilegioResources(new DAO());
	}

}
