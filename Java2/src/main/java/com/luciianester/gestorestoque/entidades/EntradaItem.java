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
@SequenceGenerator(name="entradaitem_pk", sequenceName="entradaitem_pk", initialValue=1)
public class EntradaItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="entradaitem_pk")
	private Long entradaItemId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="entradaId")
	private Entrada entrada;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="produtoId")
	private Produto produto;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="unidadeDeMedidaId")
	private UnidadeDeMedida unidadeDeMedida;
	
	@Column(length=50)
	private String loteFabricante;
	
	@Column
	private BigDecimal quantidade;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private BigDecimal valorImposto;
	
	@Transient
	private BigDecimal total;
	
	@Transient
	private BigDecimal saldo = BigDecimal.ZERO;
	
	@Transient
	private String sigla = "";
	
	public Long getEntradaItemId() {
		return entradaItemId;
	}

	public void setEntradaItemId(Long entradaItemId) {
		this.entradaItemId = entradaItemId;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public String getLoteFabricante() {
		return loteFabricante;
	}

	public void setLoteFabricante(String loteFabricante) {
		this.loteFabricante = loteFabricante;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
			if (this.valorImposto!=null) {
				this.total = this.total.add(this.valorImposto);
			}
		} else {
			this.total = BigDecimal.ZERO;
		}
	}

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
		this.calculaTotal();
	}
	
}
