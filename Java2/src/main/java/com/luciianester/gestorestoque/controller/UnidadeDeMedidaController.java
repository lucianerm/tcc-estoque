package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.MensagemTipo;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaDoProduto;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaResources;

@Controller
@RequestMapping("/produto/{paiId}/unidadedemedida")
public class UnidadeDeMedidaController extends ControllerCadastroFilho<UnidadeDeMedida>{

	private Produto produto = null;
	
	public UnidadeDeMedidaController() {
		super("unidadedemedida");
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public void pesquisar(ResourceGenerico<UnidadeDeMedida> resource) throws Exception {
		
		this.setObjeto(new UnidadeDeMedida());
		
		this.produto = new ProdutoResources(resource.getDao()).listarPeloId(this.getPaiId());
		this.addAttribute("produto", produto);
		
		List<UnidadeDeMedida> lista = resource.getDao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", this.produto))
				.addOrder(Order.asc("quantidade"))
				.list();
		
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar(ResourceGenerico<UnidadeDeMedida> resource) throws Exception {
		this.pesquisar(resource);
	}

	@Override
	public void editar(ResourceGenerico<UnidadeDeMedida> resource, Long id) throws Exception {
		
		this.pesquisar(resource);
		
		UnidadeDeMedida objeto = resource.listarPeloId(id);
		this.setObjeto(objeto);
		
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public String salvar(ResourceGenerico<UnidadeDeMedida> resource, UnidadeDeMedida objeto) throws Exception {
		
		this.pesquisar(resource);
		
		this.produto = new ProdutoResources(resource.getDao()).listarPeloId(this.getPaiId());
		objeto.setProduto(this.produto);
		this.setObjeto(objeto);
		
		String caminhoReturno = this.getCaminho()+"/cadastro";;
		
		if (objeto.getSigla()==null) {
			
			this.setMensagemErro("Sigla é Obrigatório.");
			return caminhoReturno;
			
		}
		
		if (objeto.getSigla().length()<2 || objeto.getSigla().length()>4) {
			
			this.setMensagemErro("Sigla deve ter entre 2 e 4 caracteres.");
			return caminhoReturno;
			
		}
		
		if (objeto.getQuantidade()==null || objeto.getQuantidade()<=0) {
			
			this.setMensagemErro("Quantidade deve ser maior que zero");
			return caminhoReturno;
			
		} 
		
		if (!objeto.getQuantidade().equals(1)) {
			
			Criteria listarItens = resource.getDao()
					.createCriteria(UnidadeDeMedida.class)
					.add(Restrictions.eq("produto", this.produto));
			
			if (objeto.getUnidadeDeMedidaId()!=null) {
				listarItens.add(Restrictions.ne("unidadeDeMedidaId", objeto.getUnidadeDeMedidaId()));
			}
			
			boolean temPadrao = false;
			
			List<UnidadeDeMedida> lista = listarItens.list();
			for (UnidadeDeMedida unidadeDeMedida : lista) {
				if (unidadeDeMedida.getQuantidade().equals(1)) {
					temPadrao = true;
				}
			}
			
			if (!temPadrao) {
				
				this.setMensagemErro("Não tem uma Unidade de Medida padrão.\nDeve-se criar uma unidade de Medida padrão primeiro");
				
				return this.getCaminho()+"/cadastro";
				
			}
		
		}
			
		if (objeto.getUnidadeDeMedidaId()==null) {
			resource.gravar(objeto);
		} else {
			resource.alterar(objeto);
		}
		
		return "redirect:/produto/"+this.getPaiId()+"/"+this.getCaminho()+"/"+resource.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
		
	}

	@Override
	public String excluir(ResourceGenerico<UnidadeDeMedida> resource, Long id) throws Exception {
		
		resource.remover(id);
		return "redirect:/produto/"+this.getPaiId()+"/"+this.getCaminho();
		
	}
	
	@Override
	public ResourceGenerico<UnidadeDeMedida> newResource() {
		return new UnidadeDeMedidaResources(new DAO());
	}
	
}
