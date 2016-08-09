package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.MensagemTipo;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;

@Controller
@RequestMapping("/produto/{paiId}/unidadedemedida")
public class UnidadeDeMedidaController extends ControllerCadastroFilho<UnidadeDeMedida>{

	private Produto produto = null;
	
	public UnidadeDeMedidaController() {
		super("unidadedemedida", new ResourceGenerico<UnidadeDeMedida>(UnidadeDeMedida.class));
	}

	@Override
	public Long getId(UnidadeDeMedida objeto) {
		return objeto.getUnidadeDeMedidaId();
	}
	
	@Override
	public void pesquisar() throws Exception {
		
		this.setObjeto(new UnidadeDeMedida());
		
		this.produto = new ResourceGenerico<Produto>(Produto.class).listarPeloId(this.getPaiId());
		this.addAttribute("produto", produto);
		
		List<UnidadeDeMedida> lista = this.getRes().getDao().getSessao().createCriteria(UnidadeDeMedida.class).add(Restrictions.eq("produto", this.produto)).list();
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar() throws Exception {
		this.pesquisar();
	}

	@Override
	public void editar(Long id) throws Exception {
		
		this.pesquisar();
		
		UnidadeDeMedida objeto = this.getRes().listarPeloId(id);
		this.setObjeto(objeto);
		
	}

	@Override
	public String salvar(UnidadeDeMedida objeto) throws Exception {
		
		this.produto = new ResourceGenerico<Produto>(Produto.class).listarPeloId(this.getPaiId());
		objeto.setProduto(this.produto);
		
		if (objeto.getUnidadeDeMedidaId()==null) {
			this.getRes().gravar(objeto);
		} else {
			this.getRes().alterar(objeto);
		}
		
		return "redirect:/produto/"+this.getPaiId()+"/"+this.getCaminho()+"/"+this.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
		
	}

	@Override
	public String excluir(Long id) throws Exception {
		
		this.getRes().remover(id);
		return "redirect:/produto/"+this.getPaiId()+"/"+this.getCaminho();
		
	}
	
}
