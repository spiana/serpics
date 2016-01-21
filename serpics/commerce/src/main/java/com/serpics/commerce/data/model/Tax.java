package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the tax database table.
 * 
 */
@Entity
@Table(name = "taxes")
public class Tax extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taxes_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	private Double rate;

	public Tax(){
		super();
	}
	
	public Tax(String name, Double rate){
		super();
		this.name= name;
		this.rate= rate;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}