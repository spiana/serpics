package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the pricelist database table.
 * 
 */
@Entity
@Table(name="pricelist" )
public class Pricelist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pricelist_id")
	private Long pricelistId;

	private String name;

	private Timestamp updated;

	//bi-directional many-to-one association to Catalog
    @ManyToOne
	@JoinColumn(name="catalog_id")
	private Catalog catalog;

	//bi-directional many-to-one association to PricelistDescr
	@OneToMany(mappedBy="pricelist")
	private Set<PricelistDescr> pricelistDescrs;

	//bi-directional many-to-one association to Price
	@OneToMany(mappedBy="pricelist")
	private Set<Price> prices;

	//bi-directional many-to-one association to Productffmt
	@OneToMany(mappedBy="pricelist")
	private Set<Productffmt> productffmts;

    public Pricelist() {
    }

	public Long getPricelistId() {
		return this.pricelistId;
	}

	public void setPricelistId(Long pricelistId) {
		this.pricelistId = pricelistId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
	public Set<PricelistDescr> getPricelistDescrs() {
		return this.pricelistDescrs;
	}

	public void setPricelistDescrs(Set<PricelistDescr> pricelistDescrs) {
		this.pricelistDescrs = pricelistDescrs;
	}
	
	public Set<Price> getPrices() {
		return this.prices;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}
	
	public Set<Productffmt> getProductffmts() {
		return this.productffmts;
	}

	public void setProductffmts(Set<Productffmt> productffmts) {
		this.productffmts = productffmts;
	}
	
}