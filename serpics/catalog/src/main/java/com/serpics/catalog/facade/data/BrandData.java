package com.serpics.catalog.facade.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","logo","published","brandProductNumber"})
public class BrandData extends CtentryData{
		
    protected String logo; //The Logo of the brand
    protected int brandProductNumber; //the number of product of the brand
    protected boolean published; //brand published boolean
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public int getBrandProductNumber() {
		return brandProductNumber;
	}
	public void setBrandProductNumber(int brandProductNumber) {
		this.brandProductNumber = brandProductNumber;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	
}
