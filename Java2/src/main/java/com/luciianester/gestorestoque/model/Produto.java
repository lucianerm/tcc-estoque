package com.luciianester.gestorestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="produto_pk", sequenceName="produto_pk", initialValue=1)
public class Produto {
	
	@GeneratedValue(strategy=GenerationType.AUTO, generator="produto_pk")
	@Id
	private Long produtoId;
	
	@Column(length = 200)
	private String descricao;
	
	
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
	
	
	
	

}
