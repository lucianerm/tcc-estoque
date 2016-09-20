package com.luciianester.gestorestoque.controles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleCadastroFilho;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Perfil;
import com.luciianester.gestorestoque.entidades.Privilegio;
import com.luciianester.gestorestoque.enums.Tela;
import com.luciianester.gestorestoque.recursos.perfil.PerfilRecurso;
import com.luciianester.gestorestoque.recursos.privilegio.PrivilegioRecurso;

@Controller
@RequestMapping("/perfil/{paiId}/privilegio")
public class PrivilegioControle extends ControleCadastroFilho<Privilegio> {

	private Perfil perfil = null;
	
	public PrivilegioControle() {
		super("privilegio");
	}

	@SuppressWarnings("resource")
	@Override
	public void pesquisar(RecursoGenerico<Privilegio> recurso) throws Exception {
		
		this.setObjeto(new Privilegio());
		
		this.perfil = new PerfilRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		this.addAtributo("perfil", this.perfil);
		
		@SuppressWarnings("unchecked")
		List<Privilegio> lista = (List<Privilegio>) recurso.getDao().createCriteria(Privilegio.class)
				.add(Restrictions.eq("perfil", this.perfil))
				.addOrder(Order.asc("privilegioId"))
				.list();
		
		this.setLista(lista);
		
		List<Tela> telas = new ArrayList<Tela>(Arrays.asList(Tela.values()));
		this.addAtributo("telas", telas);
		
	}

	@Override
	public void cadastrar(RecursoGenerico<Privilegio> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@Override
	public void editar(RecursoGenerico<Privilegio> recurso, Long id) throws Exception {
		this.pesquisar(recurso);
		this.setObjeto(recurso.listarPeloId(id));
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(RecursoGenerico<Privilegio> recurso, Privilegio objeto) throws Exception {
		
		this.perfil = new PerfilRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		
		objeto.setPerfil(this.perfil);
		
		if (recurso.verificaNovoCadastro(objeto)) {
			recurso.gravar(objeto);
		} else {
			recurso.alterar(objeto);
		}
		
		return "redirect:/perfil/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public String excluir(RecursoGenerico<Privilegio> recurso, Long id) throws Exception {
		recurso.remover(id);
		return "redirect:/perfil/"+this.getPaiId()+"/"+this.getCaminho();
	}

	@Override
	public RecursoGenerico<Privilegio> novoRecurso() {
		return new PrivilegioRecurso(new DAO());
	}

}
