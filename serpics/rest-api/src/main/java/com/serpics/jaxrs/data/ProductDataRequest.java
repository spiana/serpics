package com.serpics.jaxrs.data;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDataRequest {

	private Boolean published;
	private Boolean dowloadable;
	private String manufacturSku;
	private String unitMeas;
	private Double weight;
	private String weightMeas;
	private PriceDataRequest price;
	private BrandDataRequest brand;

	private Set<CategoryDataRequest> categories;
	private String metaDescription;
	private String metaKey;

	private String code;
	private String description;
	private Boolean buyable;
	private Boolean downloadable;

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Boolean getDowloadable() {
		return dowloadable;
	}

	public void setDowloadable(Boolean dowloadable) {
		this.dowloadable = dowloadable;
	}

	public String getManufacturSku() {
		return manufacturSku;
	}

	public void setManufacturSku(String manufacturSku) {
		this.manufacturSku = manufacturSku;
	}

	public String getUnitMeas() {
		return unitMeas;
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
		return weightMeas;
	}

	public void setWeightMeas(String weightMeas) {
		this.weightMeas = weightMeas;
	}

	public PriceDataRequest getPrice() {
		return price;
	}

	public void setPrice(PriceDataRequest price) {
		this.price = price;
	}

	public BrandDataRequest getBrand() {
		return brand;
	}

	public void setBrand(BrandDataRequest brand) {
		this.brand = brand;
	}

	public Set<CategoryDataRequest> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryDataRequest> categories) {
		this.categories = categories;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public Boolean getBuyable() {
		return buyable;
	}

	public Boolean getDownloadable() {
		return downloadable;
	}

	public Boolean isBuyable() {
		return buyable;
	}

	public void setBuyable(Boolean buyable) {
		this.buyable = buyable;
	}

	public Boolean isDownloadable() {
		return downloadable;
	}

	public void setDownloadable(Boolean downloadable) {
		this.downloadable = downloadable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
