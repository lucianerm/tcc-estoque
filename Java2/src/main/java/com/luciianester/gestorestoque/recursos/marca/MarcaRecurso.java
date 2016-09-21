package com.luciianester.gestorestoque.recursos.marca;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Marca;
import com.luciianester.gestorestoque.entidades.Produto;

public class MarcaRecurso extends RecursoGenerico<Marca> {

	public MarcaRecurso(DAO dao) {
		super(Marca.class, dao);
	}

	@Override
	public Long getId(Marca objeto) {
		
		return objeto.getMarcaId();
	}

	@Override
	public boolean validacaoGravar(Marca objeto) {
		
		if (objeto.getDescricao()==null) {
			this.setMensagemObrigatorio("Descrição");
			return false;
		}
		
		objeto.setDescricao(objeto.getDescricao().trim());
		
		if (objeto.getDescricao().equals("")) {
			this.setMensagemObrigatorio("Descrição");
			return false;
		}
		
		if (objeto.getDescricao().length()<=3) {
			this.setMensagemErro("Descrição deve ser maior que três");
			return false;
		}
		
		try {
			
			Criteria buscaMesmoNome = this.getDao()
					.createCriteria(Marca.class)
					.setProjection(Projections.rowCount())
					.add(Restrictions.ilike("descricao", objeto.getDescricao()));
			
			if (objeto.getMarcaId()!=null) {
				buscaMesmoNome.add(Restrictions.ne("marcaId", objeto.getMarcaId()));
			}
			
			Long quantidadeComMesmoNome = (Long) buscaMesmoNome.uniqueResult();
			
			if (quantidadeComMesmoNome!=null && quantidadeComMesmoNome>0) {
				this.setMensagemErro("Já existe outra Marca com Descrição igual a " + objeto.getDescricao());
				return false;
			}
			
		} catch (Exception e) {
			this.setMensagemErro("Erro ao validar Descrição");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validacaoAlterar(Marca objeto) {
		
		return validacaoGravar(objeto);
		
	}

	@Override
	public boolean validacaoExcluir(Marca objeto) {
		
		try {
			
			@SuppressWarnings("unchecked")
			List<Produto> lista = (List<Produto>) this.getDao().createCriteria(Produto.class)
					.add(Restrictions.eq("marca", objeto))
					.list();
			
			if (lista!=null && lista.size()>0) {
				
				String mensagem = "Esta Marca tem vínculo com os Produtos:";
				
				for (Produto produto : lista) {
					mensagem += "<br/>" + produto.getProdutoId() + " - " + produto.getDescricao();
				}
				
				this.setMensagemErro(mensagem);
				return false;
				
			}
			
		} catch (Exception e) {
			this.setMensagemErro("Erro ao validar Produtos");
			return false;
		}
		
		return true;
		
	}

}
