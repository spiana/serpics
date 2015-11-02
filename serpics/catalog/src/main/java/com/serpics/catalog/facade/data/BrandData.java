package com.serpics.catalog.facade.data;

import com.serpics.core.facade.AbstractData;

public class BrandData extends AbstractData{
	
	protected String logo;
	protected String name;
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
	
}
