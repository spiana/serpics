package com.serpics.catalog.facade.data;

import java.util.Set;

import com.serpics.base.facade.data.MediaData;

public class AbstractProductData  extends CtentryData{
	protected boolean buyable;
	protected boolean published;
	protected boolean dowloadable;
	protected String manufacturSku;
	protected String unitMeas;
	protected Double weight;
	protected String weightMeas;
	protected PriceData price;
	protected BrandData brand;

	protected Set<MediaData> medias;

	//protected CatalogData catalog;
	protected Set<CategoryData> categories;
	protected String metaDescription;
	protected String metaKey;
	

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
	public BrandData getBrand() {
		return brand;
	}
	public void setBrand(BrandData brand) {
		this.brand = brand;
	}
	public PriceData getPrice() {
		return price;
	}
	public void setPrice(PriceData price) {
		this.price = price;
	}
	public Set<CategoryData> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryData> categories) {
		this.categories = categories;
	}
	
	public Set<MediaData> getMedias() {
		return medias;
	}
	public void setMedias(Set<MediaData> medias) {
		this.medias = medias;
	}
	public boolean isBuyable() {
		return buyable;
	}
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public boolean isDowloadable() {
		return dowloadable;
	}
	public void setDowloadable(boolean dowloadable) {
		this.dowloadable = dowloadable;
	}
}
