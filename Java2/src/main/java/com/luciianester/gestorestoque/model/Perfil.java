package com.luciianester.gestorestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.luciianester.gestorestoque.enums.PerfilTipo;


@Entity
@SequenceGenerator(name="perfil_pk", sequenceName="perfil_pk", initialValue=1)
public class Perfil {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="perfil_pk")
	private Long perfilId;
	
	@Column(length = 200)
	private String descricao;
	
	@Enumerated(EnumType.ORDINAL)
	private PerfilTipo tipo;

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PerfilTipo getTipo() {
		return tipo;
	}

	public void setTipo(PerfilTipo tipo) {
		this.tipo = tipo;
	}
	
}
