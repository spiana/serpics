package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="taxes" , uniqueConstraints=@UniqueConstraint(columnNames={"store_id" , "name"}))

public class TaxCategory extends AbstractStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="taxes_id", unique=true, nullable=false)
	private Long id;

	@Pattern(regexp="[A-Za-z0-9-_]+" , message="validation.onlynumberandletter")
	@Size(min=3 , max=50)
	@Column(name="name" , length=100 )
	private String name;
	
	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
	@JoinColumn(name = "name_string_id")
	private MultilingualString description ;   
	
	private Double rate;

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

	public MultilingualString getDescription() {
		return description;
	}

	public void setDescription(MultilingualString description) {
		this.description = description;
	}
}