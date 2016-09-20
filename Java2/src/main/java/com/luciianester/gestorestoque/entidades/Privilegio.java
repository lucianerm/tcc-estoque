package com.luciianester.gestorestoque.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.luciianester.gestorestoque.enums.Tela;

@Entity
@SequenceGenerator(name="privilegio_pk", sequenceName="privilegio_pk", initialValue=1)
public class Privilegio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="privilegio_pk")
	private Long privilegioId;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="perfilId")
	private Perfil perfil;
	
	@Enumerated(EnumType.ORDINAL)
	private Tela tela;
	
	@Column
	private boolean alterar;
	
	@Column
	private boolean excluir;

	public Long getPrivilegioId() {
		return privilegioId;
	}

	public void setPrivilegioId(Long privilegioId) {
		this.privilegioId = privilegioId;
	}

	public Tela getTela() {
		return tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
