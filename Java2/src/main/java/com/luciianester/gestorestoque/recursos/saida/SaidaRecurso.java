package com.luciianester.gestorestoque.recursos.saida;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Saida;
import com.luciianester.gestorestoque.entidades.SaidaItem;
import com.luciianester.gestorestoque.recursos.saida.item.SaidaItemRecurso;

public class SaidaRecurso extends RecursoGenerico<Saida> {

	public SaidaRecurso(DAO dao) {
		super(Saida.class, dao);
	}

	@Override
	public Long getId(Saida objeto) {
		
		return objeto.getSaidaId();
	}

	@Override
	public boolean validacaoGravar(Saida objeto) {
		
		if (objeto.getData()==null) {
			this.setMensagemObrigatorio("Data");
			return false;
		}
		
		if (objeto.getCliente()!=null) {
			if (objeto.getCliente().getPessoaId()==null || objeto.getCliente().getPessoaId().equals(0l)) {
				objeto.setCliente(null);
			}
		}
		
		return true;
		
	}

	@Override
	public boolean validacaoAlterar(Saida objeto) {
		
		return validacaoGravar(objeto);
	}

	@Override
	public boolean validacaoExcluir(Saida objeto) {
		return true;
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean remover(Saida saida) throws Exception {
		
		SaidaItemRecurso saidaItemRecurso = new SaidaItemRecurso(this.getDao());
		List<SaidaItem> itens = saidaItemRecurso.listarPelaSaida(saida);
		for (SaidaItem saidaItem : itens) {
			saidaItemRecurso.remover(saidaItem);
		}
		
		return super.remover(saida);
		
	}

	@SuppressWarnings("resource")
	public BigDecimal calcularTotal(Long codigo) throws Exception {
		
		BigDecimal total = BigDecimal.ZERO;
		
		List<SaidaItem> listarPelaSaida = new SaidaItemRecurso(this.getDao()).listarPelaSaida(this.listarPeloId(codigo));
		for (SaidaItem saidaItem : listarPelaSaida) {
			total = total.add(saidaItem.getTotal());
		}
		
		return total;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Saida> listarTodos(String campoPesquisa) throws Exception {
		
		if (campoPesquisa==null || campoPesquisa.equals("")) {
			return super.listarTodos();
		} else {
			
			List<Saida> lista = (List<Saida>) this.getDao()
					.createCriteria(Saida.class)
					.createAlias("cliente", "c")
					.add(Restrictions.ilike("c.nome", "%"+campoPesquisa+"%"))
					.list();
			
			return lista;
			
		}
		
	}
	
}
