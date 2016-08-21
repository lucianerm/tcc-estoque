package com.luciianester.gestorestoque.model;

import java.math.BigDecimal;

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
@SequenceGenerator(name="privilegio_pk", sequenceName="privilegio_pk", initialValue=1)
public class Privilegio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="privilegio_pk")
	private Long privilegioId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="entradaId")
	private Entrada entrada;
	
	@Column
	private BigDecimal quantidade;
	
	@Column
	private BigDecimal valor;
	
}
