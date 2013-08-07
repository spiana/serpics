package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.serpics.core.datatype.CatalogEntryType;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "product_id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractProduct extends Ctentry implements Serializable {
	private static final long serialVersionUID = 1L;

	public AbstractProduct(short buyable, String name, String sku) {
		super();
		this.buyable = buyable;
		this.published = buyable;
		this.name = name;
		this.sku = sku;
	}

	protected short buyable;

	protected short isdownlodable;

	@Column(name = "name", nullable = false)
	protected String name;

	@Column(name = "manufacturer_sku")
	protected String manufacturerSku;

	@Column(name = "product_type", nullable = false, insertable = false, updatable = false)
	protected short productType;

	protected short published;

	@Column(name = "sku")
	protected String sku;

	@Column(name = "unit_meas")
	protected String unitMeas;

	protected float weight;

	@Column(name = "weight_meas")
	protected String weightMeas;

	// bi-directional many-to-one association to Price
	@OneToMany(mappedBy = "product")
	protected Set<Price> prices;

	// bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name = "brands_id")
	protected Brand brand;

	// bi-directional many-to-one association to Productffmt
	@OneToMany(mappedBy = "product")
	protected Set<Productffmt> productffmts;

	public AbstractProduct() {
		super();
		this.setCtentryType(CatalogEntryType.PRODUCT);
	}

	public short getBuyable() {
		return this.buyable;
	}

	public void setBuyable(short buyable) {
		this.buyable = buyable;
	}

	public short getIsdownlodable() {
		return this.isdownlodable;
	}

	public void setIsdownlodable(short isdownlodable) {
		this.isdownlodable = isdownlodable;
	}

	public String getManufacturerSku() {
		return this.manufacturerSku;
	}

	public void setManufacturerSku(String manufacturerSku) {
		this.manufacturerSku = manufacturerSku;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public short getProductType() {
		return this.productType;
	}

	public void setProductType(short productType) {
		this.productType = productType;
	}

	public short getPublished() {
		return this.published;
	}

	public void setPublished(short published) {
		this.published = published;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getUnitMeas() {
		return this.unitMeas;
	}

	public void setUnitMeas(String unitMeas) {
		this.unitMeas = unitMeas;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
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

}