package com.luciianester.gestorestoque.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.MensagemTipo;
import com.luciianester.gestorestoque.model.Entrada;
import com.luciianester.gestorestoque.model.EntradaItem;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;
import com.luciianester.gestorestoque.resources.entrada.EntradaResources;
import com.luciianester.gestorestoque.resources.entrada.item.EntradaItemResources;
import com.luciianester.gestorestoque.resources.produto.ProdutoResources;
import com.luciianester.gestorestoque.resources.unidadedemedida.UnidadeDeMedidaResources;

@Controller
@RequestMapping("/entrada/{paiId}/entradaitem")
public class EntradaItemController extends ControllerCadastroFilho<EntradaItem>{

	private Entrada entrada = null;
	
	public EntradaItemController() {
		super("entradaitem", new EntradaItemResources());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() throws Exception {

		this.entrada = new EntradaResources().listarPeloId(this.getPaiId());
		this.addAttribute("entrada", entrada);

		EntradaItem entradaItem = new EntradaItem();
		entradaItem.setEntrada(this.entrada);
		this.setObjeto(entradaItem);
		
		List<EntradaItem> lista = this.getRes().getDao().getSessao()
				.createCriteria(EntradaItem.class)
				.add(Restrictions.eq("entrada", this.entrada))
				.addOrder(Order.asc("entradaItemId"))
				.list();
		
		this.setLista(lista);

		
		List<Produto> listProdutos = new ProdutoResources().listarTodos();
		this.addAttribute("listProdutos", listProdutos);
		
		List<UnidadeDeMedida> listUnidadeDeMedida = new ArrayList<>();
		this.addAttribute("listUnidadeDeMedida", listUnidadeDeMedida);
		
	}

	@Override
	public void cadastrar() throws Exception {
		this.pesquisar();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void editar(Long id) throws Exception {
		
		this.pesquisar();
		
		EntradaItem entradaItem = this.getRes().listarPeloId(id);
		this.setObjeto(entradaItem);
		
		List<UnidadeDeMedida> listUnidadeDeMedida = new ArrayList<>();
		
		if (entradaItem.getProduto()!=null && entradaItem.getProduto().getProdutoId()!=null) {
			listUnidadeDeMedida = new UnidadeDeMedidaResources().getDao().getSessao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", entradaItem.getProduto()))
				.addOrder(Order.asc("descricao"))
				.list();
		}
		
		this.addAttribute("listUnidadeDeMedida", listUnidadeDeMedida);
		
	}

	@Override
	public String salvar(EntradaItem objeto) throws Exception {
		
		this.entrada = new EntradaResources().listarPeloId(this.getPaiId());
		objeto.setEntrada(this.entrada);
		this.setObjeto(objeto);
		
		if (objeto.getEntradaItemId()==null) {
			this.getRes().gravar(objeto);
		} else {
			this.getRes().alterar(objeto);
		}
		
		return "redirect:/entrada/"+this.getPaiId()+"/"+this.getCaminho()+"/"+this.getRes().getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
		
	}

	@Override
	public String excluir(Long id) throws Exception {
		
		this.getRes().remover(id);
		return "redirect:/entrada/"+this.getPaiId()+"/"+this.getCaminho();
		
	}
	
}
