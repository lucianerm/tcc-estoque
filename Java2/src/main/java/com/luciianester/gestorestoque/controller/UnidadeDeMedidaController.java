package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
		
		List<UnidadeDeMedida> lista = this.getRes().getDao().getSessao()
				.createCriteria(UnidadeDeMedida.class)
				.add(Restrictions.eq("produto", this.produto))
				.addOrder(Order.asc("quantidade"))
				.list();
		
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

	@SuppressWarnings("unchecked")
	@Override
	public String salvar(UnidadeDeMedida objeto) throws Exception {
		
		this.pesquisar();
		
		this.produto = new ResourceGenerico<Produto>(Produto.class).listarPeloId(this.getPaiId());
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
			
			Criteria listarItens = this.getRes().getDao().getSessao()
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
