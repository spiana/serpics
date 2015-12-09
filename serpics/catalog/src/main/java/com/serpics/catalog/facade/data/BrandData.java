package com.serpics.catalog.facade.data;

import com.serpics.core.facade.AbstractData;

public class BrandData extends AbstractData{
	
	protected String logo;
	protected String name;
	protected int brandProductNumber;
	protected int published;
	
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
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
		this.published = published;
	}
	public int getBrandProductNumber() {
		return brandProductNumber;
	}
	public void setBrandProductNumber(int brandProductNumber) {
		this.brandProductNumber = brandProductNumber;
	}
	
}
