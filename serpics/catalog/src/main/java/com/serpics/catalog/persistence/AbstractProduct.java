package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.serpics.core.datatype.CatalogEntryType;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "abstractProducts")
@DiscriminatorValue("1")
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.INTEGER)
@PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "ctentry_id")
public abstract class AbstractProduct extends Ctentry implements Serializable {
	private static final long serialVersionUID = 1L;

	public AbstractProduct(Integer buyable, String sku) {
		super();
		this.buyable = buyable;
		this.published = buyable;
		this.code = sku;
		this.downlodable = 0;
		this.ctentryType = (short) 1;
	}

	public AbstractProduct() {
		super();
		this.buyable = this.published = this.downlodable = 0;
		this.ctentryType = CatalogEntryType.PRODUCT;
	}

	@Column(name = "buyable", nullable = false)
	protected Integer buyable;

	@Column(name = "downlodable", nullable = false)
	protected Integer downlodable;

	@Column(name = "manufacturer_sku")
	protected String manufacturerSku;

	@Column(name = "product_type", nullable = false)
	protected Integer productType;

	@Column(name = "published", nullable = false)
	protected Integer published;

	@Column(name = "unit_meas")
	protected String unitMeas;

	@Column(name = "weight")
	protected Double weight;

	@Column(name = "weight_meas")
	protected String weightMeas;

	// bi-directional many-to-one association to Price
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	protected Set<Price> prices;

	// bi-directional many-to-one association to Brand
	@ManyToOne(optional = true)
	@JoinColumn(name = "brand_id")
	protected Brand brand;

	// bi-directional many-to-one association to Productffmt
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	protected Set<Productffmt> productffmts;

	// bi-directional many-to-one association to Catalog
	@ManyToOne(optional = false  )
	@JoinColumn(name = "catalog_id" )
	protected Catalog catalog;

	public String getManufacturerSku() {
		return this.manufacturerSku;
	}

	public void setManufacturerSku(String manufacturerSku) {
		this.manufacturerSku = manufacturerSku;
	}

	public String getUnitMeas() {
		return this.unitMeas;
	}

	public void setUnitMeas(String unitMeas) {
		this.unitMeas = unitMeas;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightMeas() {
		return this.weightMeas;
	}

	public void setWeightMeas(String weightMeas) {
		this.weightMeas = weightMeas;
	}

	public Set<Price> getPrices() {
		return this.prices;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Set<Productffmt> getProductffmts() {
		return this.productffmts;
	}

	public void setProductffmts(Set<Productffmt> productffmts) {
		this.productffmts = productffmts;
	}

	public Integer getBuyable() {
		return buyable;
	}

	public void setBuyable(Integer buyable) {
		this.buyable = buyable;
	}

	public Integer getDownlodable() {
		return downlodable;
	}

	public void setDownlodable(Integer downlodable) {
		this.downlodable = downlodable;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@PrePersist
	public void prepersist(){
		if (this.url == null)
			this.url = "/" + getCatalog().getCode() + "/" + getCode();
	}
}