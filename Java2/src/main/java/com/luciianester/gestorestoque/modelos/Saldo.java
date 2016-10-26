package com.luciianester.gestorestoque.modelos;

import java.math.BigDecimal;

public class Saldo {
	
	private Long produtoId;
	private String descricao;
	private String sigla;
	private BigDecimal entradas;
	private BigDecimal saidas;
	private BigDecimal saldoMinimo;
	private BigDecimal saldo;
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
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getEntradas() {
		return entradas;
	}
	public void setEntradas(BigDecimal entradas) {
		this.entradas = entradas;
	}
	public BigDecimal getSaldoMinimo() {
		return saldoMinimo;
	}
	public void setSaldoMinimo(BigDecimal saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}
	
}
