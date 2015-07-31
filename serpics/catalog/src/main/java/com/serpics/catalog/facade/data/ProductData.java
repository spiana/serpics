package com.serpics.catalog.facade.data;



public class ProductData  extends CtentryData{
	protected int buyable;
	protected int published;
	protected int dowloadable;
	protected String manufacturSku;
	protected String unitMeas;
	protected Double weight;
	protected String weightMeas;
	//protected Set<PriceData> prices;
	//protected BrandData brand;
	//protected CatalogData catalog;
	//protected Categories categories;
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
	
}
