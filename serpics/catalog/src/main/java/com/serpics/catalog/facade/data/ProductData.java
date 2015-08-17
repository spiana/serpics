package com.serpics.catalog.facade.data;

import java.util.Set;






public class ProductData  extends CtentryData{
	protected int buyable;
	protected int published;
	protected int dowloadable;
	protected String manufacturSku;
	protected String unitMeas;
	protected Double weight;
	protected String weightMeas;
	protected PriceData prices;
	protected BrandData brand;
	protected Set<MediaData> medias;
	
	//protected CatalogData catalog;
	protected Set<CategoryData> categories;
	protected String metaDescription;
	protected String metaKey;
	public int getBuyable() {
		return buyable;
	}
	public void setBuyable(int buyable) {
		this.buyable = buyable;
	}
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
		this.published = published;
	}
	public int getDowloadable() {
		return dowloadable;
	}
	public void setDowloadable(int dowloadable) {
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

	public PriceData getPrices() {
		return prices;
	}
	public void setPrice(PriceData prices) {
		this.prices = prices;
	}
	public Set<MediaData> getMedia() {
		return medias;
	}
	public void setMedias(Set<MediaData> medias) {
		this.medias = medias;
	}
	public Set<CategoryData> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryData> categories) {
		this.categories = categories;
	}
}
