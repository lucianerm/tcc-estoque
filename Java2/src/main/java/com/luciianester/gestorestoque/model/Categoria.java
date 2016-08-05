package com.luciianester.gestorestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="categoria_pk", sequenceName="categoria_pk", initialValue=1)
public class Categoria {
	
	@GeneratedValue(strategy=GenerationType.AUTO, generator="categoria_pk")
	@Id
	private Long categoriaId;
	@Column(length = 200)
	private String descricao;


	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaid(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
