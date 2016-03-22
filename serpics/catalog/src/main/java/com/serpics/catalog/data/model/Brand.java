package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.serpics.catalog.data.CatalogEntryType;

/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name = "brands")
@DiscriminatorValue("0")
public class Brand extends Ctentry implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name = "logo_src")
	private String logoSrc;
	
	private boolean published;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "brand" , fetch=FetchType.LAZY)
	private Set<Product> products;
	
	public Brand() {
		this.ctentryType = CatalogEntryType.BRAND;
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

	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
	
	@PrePersist
    @Override
    public void beforePersist() {
        if (this.url == null)
            this.url = "/" + getCatalog().getCode() + "/b/" + getCode();
        super.beforePersist();
    }

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}