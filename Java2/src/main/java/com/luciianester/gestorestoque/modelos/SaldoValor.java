package com.luciianester.gestorestoque.modelos;

import java.math.BigDecimal;

public class SaldoValor {
	
	private Long produtoId;
	private String descricao;
	private String data;
	private String sigla;
	private BigDecimal entradas = BigDecimal.ZERO;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private BigDecimal saidas = BigDecimal.ZERO;
	private BigDecimal saidasValorUnitario = BigDecimal.ZERO;
	private BigDecimal saidasValorTotal = BigDecimal.ZERO;
	private BigDecimal ganho = BigDecimal.ZERO;
	
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public BigDecimal getSaidas() {
		return saidas;
	}
	public void setSaidas(BigDecimal saidas) {
		this.saidas = saidas;
	}
	public BigDecimal getEntradas() {
		return entradas;
	}
	public void setEntradas(BigDecimal entradas) {
		this.entradas = entradas;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getSaidasValorUnitario() {
		return saidasValorUnitario;
	}
	public void setSaidasValorUnitario(BigDecimal saidasValorUnitario) {
		this.saidasValorUnitario = saidasValorUnitario;
	}
	public BigDecimal getSaidasValorTotal() {
		return saidasValorTotal;
	}
	public void setSaidasValorTotal(BigDecimal saidasValorTotal) {
		this.saidasValorTotal = saidasValorTotal;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BigDecimal getGanho() {
		return ganho;
	}
	public void setGanho(BigDecimal lucro) {
		this.ganho = lucro;
	}
	
}
