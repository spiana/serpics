package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;

/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name = "brands")
public class Brand extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "brand_id")
	private Long id;

	@Column(name = "logo_src")
	private String logoSrc;

	private String name;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "brand" , fetch=FetchType.LAZY)
	private Set<AbstractProduct> products;
	
	@ManyToOne(optional = false  )
	@JoinColumn(name = "catalog_id" )
	private Catalog catalog;
	
	
	public Brand() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long brandsId) {
		this.id = brandsId;
	}

	public String getLogoSrc() {
		return this.logoSrc;
	}

	public void setLogoSrc(String logoSrc) {
		this.logoSrc = logoSrc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AbstractProduct> getProducts() {
		return this.products;
	}

	public void setProducts(Set<AbstractProduct> products) {
		this.products = products;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}