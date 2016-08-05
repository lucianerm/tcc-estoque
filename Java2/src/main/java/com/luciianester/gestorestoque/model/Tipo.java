package com.luciianester.gestorestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="tipo_pk", sequenceName="tipo_pk", initialValue=1)
public class Tipo {
	@GeneratedValue(strategy=GenerationType.AUTO, generator="tipo_pk")
	@Id
	private Long tipoId;
	private String tipo;
	private Double quantidade;
	
	public Long getTipoId() {
		return tipoId;
	}
	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	

}
