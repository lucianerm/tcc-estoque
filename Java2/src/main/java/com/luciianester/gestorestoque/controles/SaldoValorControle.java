package com.luciianester.gestorestoque.controles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.Produto;
import com.luciianester.gestorestoque.entidades.SaidaItem;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.modelos.SaldoValor;
import com.luciianester.gestorestoque.modelos.SaldoValores;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemDoProduto;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.produto.ProdutoRecurso;
import com.luciianester.gestorestoque.recursos.saida.item.SaidaItemRecurso;

@Controller
@RequestMapping(value="/saldovalor")
public class SaldoValorControle {
	
	
	@SuppressWarnings("resource")
	@RequestMapping("")
	public String pesquisar(Model modelo) throws Exception {
		
		SaldoValores saldo = new SaldoValores();
		modelo.addAttribute("objeto", saldo);
		
		List<Produto> listaProdutos = new ProdutoRecurso(new DAO()).listarTodos();
		modelo.addAttribute("listaProdutos", listaProdutos);
		
		return "saldovalor/pesquisa";
		
		
	}
	
	@SuppressWarnings("resource")
	@RequestMapping("/pesquisar")
	public String pesquisarSaldos(@ModelAttribute("objeto") SaldoValores saldos, Model modelo) throws Exception {
		
		DAO dao = new DAO();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try (
			
			EntradaItemRecurso entradaItemRecurso = new EntradaItemRecurso(dao);
			ProdutoRecurso produtoRecurso = new ProdutoRecurso(dao);
			SaidaItemRecurso saidaItemRecurso = new SaidaItemRecurso(dao);
			
		) {
			
			Produto produto = produtoRecurso.listarPeloId(saldos.getProdutoId());
			EntradaItemDoProduto listaEntradaItem = entradaItemRecurso.listarPeloProdutoOrdenandoPelaData(saldos.getProdutoId(), true);
			
			UnidadeDeMedida unidadePrincipal = (UnidadeDeMedida) dao.createCriteria(UnidadeDeMedida.class)
					.add(Restrictions.eq("produto", produto))
					.add(Restrictions.eq("quantidade", 1))
					.uniqueResult();
			
			for (EntradaItem entradaItem : listaEntradaItem.getLista()) {
				
				SaldoValor saldo = new SaldoValor();
				saldo.setProdutoId(entradaItem.getProduto().getProdutoId());
				saldo.setDescricao(entradaItem.getProduto().getDescricao());
				saldo.setData(df.format(entradaItem.getEntrada().getData()));
				saldo.setEntradas(BigDecimal.ZERO);
				saldo.setSaidas(BigDecimal.ZERO);
				
				if (unidadePrincipal!=null) {
					saldo.setSigla(unidadePrincipal.getSigla());
				}
				
				BigDecimal entradas = BigDecimal.ZERO;
				entradas = entradas.add(entradaItem.getQuantidade().multiply(BigDecimal.valueOf(entradaItem.getUnidadeDeMedida().getQuantidade())));
				
				List<SaidaItem> listaSaidaItem = saidaItemRecurso.listarPelaEntrada(entradaItem);
				BigDecimal saidas = BigDecimal.ZERO;
				BigDecimal saidasValorTotal = BigDecimal.ZERO;
				for (SaidaItem saidaItem : listaSaidaItem) {
					saidas = saidas.add(saidaItem.getQuantidade().multiply(BigDecimal.valueOf(saidaItem.getUnidadeDeMedida().getQuantidade())));
					saidasValorTotal = saidasValorTotal.add(saidaItem.getTotal());
				}
				
				saldo.setEntradas(saldo.getEntradas().add(entradas));
				saldo.setValorTotal(entradaItem.getTotal());
				if (!saldo.getEntradas().equals(BigDecimal.ZERO)) {
					saldo.setValorUnitario(entradaItem.getTotal().divide(saldo.getEntradas(), RoundingMode.DOWN));
				}
				saldo.setSaidas(saidas);
				saldo.setSaidasValorTotal(saidasValorTotal);
				if (!saldo.getSaidas().equals(BigDecimal.ZERO)) {
					saldo.setSaidasValorUnitario(saidasValorTotal.divide(saldo.getSaidas(), RoundingMode.DOWN));
				}
				
				saldo.setGanho(saldo.getSaidasValorUnitario().subtract(saldo.getValorUnitario()).multiply(saldo.getSaidas()));
				
				saldos.getListaProdutos().add(saldo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Produto> listaProdutos = new ProdutoRecurso(dao).listarTodos();
		modelo.addAttribute("listaProdutos", listaProdutos);
		modelo.addAttribute("objeto", saldos);
		
		return "saldovalor/pesquisa";
		
		/*
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date data1 = df.parse(saldos.getData());
		java.sql.Date data = new java.sql.Date(data1.getTime());
		
		DAO dao = new DAO();
		Map<Long, Saldo> mapSaldos = new HashMap<>();
		
		try (
			EntradaRecurso entradaRecurso = new EntradaRecurso(dao);
			EntradaItemRecurso entradaItemRecurso = new EntradaItemRecurso(dao);
			SaidaItemRecurso saidaItemRecurso = new SaidaItemRecurso(dao);
		) {
			
			List<Entrada> listaEntradas = entradaRecurso.listarPelaData(data);
			for (Entrada entrada : listaEntradas) {
				
				List<EntradaItem> listaEntradaItem = entradaItemRecurso.listarPelaEntrada(entrada);
				
				for (EntradaItem entradaItem : listaEntradaItem) {
					
					Saldo saldo = mapSaldos.get(entradaItem.getProduto().getProdutoId());
					
					if (saldo==null) {
						
						saldo = new Saldo();
						saldo.setProdutoId(entradaItem.getProduto().getProdutoId());
						saldo.setDescricao(entradaItem.getProduto().getDescricao());
						saldo.setEntradas(BigDecimal.ZERO);
						saldo.setSaidas(BigDecimal.ZERO);
						saldo.setSaldo(BigDecimal.ZERO);
						
						UnidadeDeMedida unidadePrincipal = (UnidadeDeMedida) dao.createCriteria(UnidadeDeMedida.class)
							.add(Restrictions.eq("produto", entradaItem.getProduto()))
							.add(Restrictions.eq("quantidade", 1))
							.uniqueResult();

						if (unidadePrincipal!=null) {
							saldo.setSigla(unidadePrincipal.getSigla());
						}
						
						
					}
					
					BigDecimal entradas = BigDecimal.ZERO;
					entradas = entradas.add(entradaItem.getQuantidade().multiply(BigDecimal.valueOf(entradaItem.getUnidadeDeMedida().getQuantidade())));
					
					List<SaidaItem> listaSaidaItem = saidaItemRecurso.listarPelaEntrada(entradaItem, data);
					
					BigDecimal saidas = BigDecimal.ZERO;
					for (SaidaItem saidaItem : listaSaidaItem) {
						saidas = saidas.add(saidaItem.getQuantidade().multiply(BigDecimal.valueOf(saidaItem.getUnidadeDeMedida().getQuantidade())));
					}
					
					saldo.setEntradas(saldo.getEntradas().add(entradas));
					saldo.setSaidas(saldo.getSaidas().add(saidas));
					
					mapSaldos.put(entradaItem.getProduto().getProdutoId(), saldo);
					
				}
				
				
			}
			
			List<Saldo> produtos = new ArrayList<>();
			for (Map.Entry<Long, Saldo> entry : mapSaldos.entrySet()) {
				Saldo saldo = entry.getValue();
				saldo.setSaldo(saldo.getEntradas().subtract(saldo.getSaidas()));
				produtos.add(saldo);
			}
			saldos.setListaProdutos(produtos);
			
			modelo.addAttribute("objeto", saldos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
}