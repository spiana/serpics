package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="taxes" )
public class Tax extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="taxes_id", unique=true, nullable=false)
	private Long id;

	@Column(name="name" , unique=true)
	private String name;

	private Double rate;
}