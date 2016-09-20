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

import com.luciianester.gestorestoque.enums.Situacao;


@Entity
@SequenceGenerator(name="usuario_pk", sequenceName="usuario_pk", initialValue=1)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="usuario_pk")
	private Long usuarioId;
	
	@Column(length = 200)
	private String nome;
	
	@Column(length = 200)
	private String nomeDeAcesso;
	
	@Column(length = 200)
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	private Situacao situacao = Situacao.ATIVO;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="perfilId")
	private Perfil perfil;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeDeAcesso() {
		return nomeDeAcesso;
	}

	public void setNomeDeAcesso(String nomeDeAcesso) {
		this.nomeDeAcesso = nomeDeAcesso;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
