package com.luciianester.gestorestoque.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="marca_pk", sequenceName="marca_pk", initialValue=1)
public class Marca {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="marca_pk")
	private Long marcaId;
	
	@Column(length = 200)
	private String descricao;

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
