package com.luciianester.gestorestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="unidadedemedida_pk", sequenceName="unidadedemedida_pk", initialValue=1)
public class UnidadeDeMedida {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="unidadedemedida_pk")
	private Long unidadeDeMedidaId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="produtoId")
	private Produto produto;
	
	@Column(length = 4)
	private String sigla;
	
	@Column(length = 200)
	private String descricao;
	
	@Column
	private Integer quantidade;

	public Long getUnidadeDeMedidaId() {
		return unidadeDeMedidaId;
	}
	public void setUnidadeDeMedidaId(Long unidadeDeMedidaId) {
		this.unidadeDeMedidaId = unidadeDeMedidaId;
	}
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
