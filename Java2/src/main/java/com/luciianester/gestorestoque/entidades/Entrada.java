package com.luciianester.gestorestoque.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@SequenceGenerator(name="entrada_pk", sequenceName="entrada_pk", initialValue=1)
public class Entrada {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="entrada_pk")
	private Long entradaId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fornecedorId")
	private Pessoa fornecedor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuarioId")
	private Usuario usuario;
	
	public Long getEntradaId() {
		return entradaId;
	}

	public void setEntradaId(Long entradaId) {
		this.entradaId = entradaId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pessoa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public Entrada() {
		this.data = new Date();
	}
	
}
