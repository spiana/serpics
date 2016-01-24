package com.serpics.catalog.facade.data;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.serpics.core.facade.AbstractData;


@JsonPropertyOrder({"id","uuid","created","updated","name","logo","published","brandProductNumber"})
public class BrandData extends AbstractData{
		
    protected String logo; //The Logo of the brand
    protected String name; //The Name of the brand
    protected int brandProductNumber; //the number of product of the brand
    protected boolean published; //brand published boolean
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
