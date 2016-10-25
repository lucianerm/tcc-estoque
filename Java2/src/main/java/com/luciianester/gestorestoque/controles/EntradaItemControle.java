package com.luciianester.gestorestoque.controles;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.ControleCadastroFilho;
import com.luciianester.gestorestoque.base.MensagemTipo;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.recursos.entrada.EntradaRecurso;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaRecurso;

@Controller
@RequestMapping("/entrada/{paiId}/entradaitem")
public class EntradaItemControle extends ControleCadastroFilho<EntradaItem>{

	private Entrada entrada = null;
	
	public EntradaItemControle() {
		super("entradaitem");
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public void pesquisar(RecursoGenerico<EntradaItem> recurso) throws Exception {

		this.entrada = new EntradaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		this.addAtributo("entrada", entrada);

		EntradaItem entradaItem = new EntradaItem();
		entradaItem.setEntrada(this.entrada);
		this.setObjeto(entradaItem);
		
		List<EntradaItem> lista = recurso.getDao()
				.createCriteria(EntradaItem.class)
				.add(Restrictions.eq("entrada", this.entrada))
				.addOrder(Order.asc("entradaItemId"))
				.list();
		
		this.setLista(lista);

		
		List<Produto> listaProdutos = new ProdutoRecurso(recurso.getDao()).listarTodos();
		this.addAtributo("listaProdutos", listaProdutos);
		
		List<UnidadeDeMedida> listaUnidadeDeMedida = new ArrayList<>();
		this.addAtributo("listaUnidadeDeMedida", listaUnidadeDeMedida);

		this.addAtributo("totalEntrada", new EntradaRecurso(recurso.getDao()).calcularTotal(this.getPaiId()));
		
	}

	@Override
	public void cadastrar(RecursoGenerico<EntradaItem> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void editar(RecursoGenerico<EntradaItem> recurso, Long id) throws Exception {
		
		this.pesquisar(recurso);
		
		EntradaItem entradaItem = recurso.listarPeloId(id);
		this.setObjeto(entradaItem);
		
		List<UnidadeDeMedida> listaUnidadeDeMedida = new ArrayList<>();
		
		if (entradaItem.getProduto()!=null && entradaItem.getProduto().getProdutoId()!=null) {
			listaUnidadeDeMedida = new UnidadeDeMedidaRecurso(recurso.getDao()).getDao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", entradaItem.getProduto()))
				.addOrder(Order.asc("descricao"))
				.list();
		}
		
		this.addAtributo("listaUnidadeDeMedida", listaUnidadeDeMedida);
		
	}

	@SuppressWarnings("resource")
	@Override
	public String salvar(RecursoGenerico<EntradaItem> recurso, EntradaItem objeto) throws Exception {
		
		this.entrada = new EntradaRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		objeto.setEntrada(this.entrada);
		this.setObjeto(objeto);
		
		if (objeto.getEntradaItemId()==null) {
			recurso.gravar(objeto);
		} else {
			recurso.alterar(objeto);
		}
		
		return "redirect:/entrada/"+this.getPaiId()+"/"+this.getCaminho()+"/"+recurso.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
		
	}

	@Override
	public String excluir(RecursoGenerico<EntradaItem> recurso, Long id) throws Exception {
		
		recurso.remover(id);
		return "redirect:/entrada/"+this.getPaiId()+"/"+this.getCaminho();
		
	}

	@Override
	public RecursoGenerico<EntradaItem> novoRecurso() {
		return new EntradaItemRecurso(new DAO());
	}
	
	@RequestMapping("/novoproduto/{entradaItemId}")
	public String novoProduto(@PathVariable("entradaItemId") Long entradaItemId) {
		
		this.addAtributo("voltarTela", "entradaitem");
		
		System.out.println("novoproduto");
		return "redirect:/produto/cadastro";
		
	}
	
}
