package com.luciianester.gestorestoque.controles;

import java.math.BigDecimal;
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

import com.luciianester.gestorestoque.base.ControleCadastroFilho;
import com.luciianester.gestorestoque.base.MensagemTipo;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.unidadedemedida.UnidadeDeMedidaRecurso;

@Controller
@RequestMapping("/produto/{paiId}/unidadedemedida")
public class UnidadeDeMedidaControle extends ControleCadastroFilho<UnidadeDeMedida>{

	private Produto produto = null;
	
	public UnidadeDeMedidaControle() {
		super("unidadedemedida");
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public void pesquisar(RecursoGenerico<UnidadeDeMedida> recurso) throws Exception {
		
		this.setObjeto(new UnidadeDeMedida());
		
		this.produto = new ProdutoRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		this.addAtributo("produto", produto);
		
		List<UnidadeDeMedida> lista = recurso.getDao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", this.produto))
				.addOrder(Order.asc("quantidade"))
				.list();
		
		this.setLista(lista);
		
	}

	@Override
	public void cadastrar(RecursoGenerico<UnidadeDeMedida> recurso) throws Exception {
		this.pesquisar(recurso);
	}

	@Override
	public void editar(RecursoGenerico<UnidadeDeMedida> recurso, Long id) throws Exception {
		
		this.pesquisar(recurso);
		
		UnidadeDeMedida objeto = recurso.listarPeloId(id);
		this.setObjeto(objeto);
		
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public String salvar(RecursoGenerico<UnidadeDeMedida> recurso, UnidadeDeMedida objeto) throws Exception {
		
		this.pesquisar(recurso);
		
		this.produto = new ProdutoRecurso(recurso.getDao()).listarPeloId(this.getPaiId());
		objeto.setProduto(this.produto);
		this.setObjeto(objeto);
		
		String caminhoRetorno = this.getCaminho()+"/cadastro";
		
		if (objeto.getSigla()==null) {
			
			this.setMensagemErro("Sigla é Obrigatório.");
			return caminhoRetorno;
			
		}
		
		if (objeto.getSigla().length()<2 || objeto.getSigla().length()>4) {
			
			this.setMensagemErro("Sigla deve ter entre 2 e 4 caracteres.");
			return caminhoRetorno;
			
		}
		
		if (objeto.getQuantidade()==null || objeto.getQuantidade()<=0) {
			
			this.setMensagemErro("Quantidade deve ser maior que zero");
			return caminhoRetorno;
			
		} 
		
		if (!objeto.getQuantidade().equals(1)) {
			
			Criteria listarItens = recurso.getDao()
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
			recurso.gravar(objeto);
		} else {
			recurso.alterar(objeto);
		}
		
		return "redirect:/produto/"+this.getPaiId()+"/"+this.getCaminho()+"/"+recurso.getId(objeto)+"?tipo="+MensagemTipo.SALVOU_SUCESSO;
		
	}

	@Override
	public String excluir(RecursoGenerico<UnidadeDeMedida> recurso, Long id) throws Exception {
		
		recurso.remover(id);
		return "redirect:/produto/"+this.getPaiId()+"/"+this.getCaminho();
		
	}
	
	@Override
	public RecursoGenerico<UnidadeDeMedida> novoRecurso() {
		return new UnidadeDeMedidaRecurso(new DAO());
	}
	
	@RequestMapping(value="/{id}/valor", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody BigDecimal buscaValor(@PathVariable("id") Long id) throws Exception {
		
		return this.criaRecurso().listarPeloId(id).getValorDeVenda();
		 	
	}
}
