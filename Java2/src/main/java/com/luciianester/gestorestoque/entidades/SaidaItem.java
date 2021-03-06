package com.luciianester.gestorestoque.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name="saidaitem_pk", sequenceName="saidaitem_pk", initialValue=1)
public class SaidaItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="saidaitem_pk")
	private Long saidaItemId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="saidaId")
	private Saida saida;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="entradaItemId")
	private EntradaItem entradaItem;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="unidadeDeMedidaId")
	private UnidadeDeMedida unidadeDeMedida;
	
	@Column
	private BigDecimal quantidade;
	
	@Column
	private BigDecimal valor;

	@Transient
	private BigDecimal total;
	
	@Transient
	private Long produtoId;
	

	public Long getSaidaItemId() {
		return saidaItemId;
	}

	public void setSaidaItemId(Long saidaItemId) {
		this.saidaItemId = saidaItemId;
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
	}

	public EntradaItem getEntradaItem() {
		return entradaItem;
	}

	public void setEntradaItem(EntradaItem entradaItem) {
		this.entradaItem = entradaItem;
	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
		this.calculaTotal();
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
		this.calculaTotal();
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public BigDecimal getTotal() {
		this.calculaTotal();
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	private void calculaTotal() {
		if (this.valor!=null && this.quantidade!=null) {
			this.total = this.valor.multiply(this.quantidade);
		} else {
			this.total = BigDecimal.ZERO;
		}
	}
}
