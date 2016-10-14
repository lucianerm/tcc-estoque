package com.luciianester.gestorestoque.controles;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Entrada;
import com.luciianester.gestorestoque.entidades.EntradaItem;
import com.luciianester.gestorestoque.entidades.SaidaItem;
import com.luciianester.gestorestoque.entidades.UnidadeDeMedida;
import com.luciianester.gestorestoque.modelos.Saldo;
import com.luciianester.gestorestoque.modelos.Saldos;
import com.luciianester.gestorestoque.recursos.entrada.EntradaRecurso;
import com.luciianester.gestorestoque.recursos.entrada.item.EntradaItemRecurso;
import com.luciianester.gestorestoque.recursos.saida.item.SaidaItemRecurso;

@Controller
@RequestMapping(value="/saldos")
public class SaldosControle {
	
	
	@RequestMapping("")
	public String pesquisar(Model modelo) throws Exception {
		
		Saldos saldo = new Saldos();
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		saldo.setData(df.format(new Date()));
		modelo.addAttribute("objeto", saldo);
		
		return "saldos/pesquisa";
		
		
	}
	
	@RequestMapping("/pesquisar")
	public String gravar(@ModelAttribute("objeto") Saldos saldos, Model modelo) throws Exception {
		
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
		
		return "saldos/pesquisa";
	}
	
}