package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name="brands" )
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="brands_id")
	private Long brandsId;

	@Column(name="logo_src")
	private String logoSrc;

	private String name;

	@Temporal( TemporalType.TIMESTAMP)
	private Date updated;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="brand")
	private Set<AbstractProduct> products;

    public Brand() {
    }

	public Long getBrandsId() {
		return this.brandsId;
	}

	public void setBrandsId(Long brandsId) {
		this.brandsId = brandsId;
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

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Set<AbstractProduct> getProducts() {
		return this.products;
	}

	public void setProducts(Set<AbstractProduct> products) {
		this.products = products;
	}
	
}