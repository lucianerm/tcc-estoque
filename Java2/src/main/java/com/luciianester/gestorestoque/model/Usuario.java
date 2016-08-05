package com.luciianester.gestorestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="usuario_pk", sequenceName="usuario_pk", initialValue=1)
public class Usuario {

	
	//@GeneratedValue
	@GeneratedValue(strategy=GenerationType.AUTO, generator="usuario_pk")
	@Id
	private Long id;
	
	private String name;
	
	private String country;
	
	public Usuario() {
	}
	
	public Usuario(String name, String country) {
		super();
		this.name = name;
		this.setCountry(country);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}