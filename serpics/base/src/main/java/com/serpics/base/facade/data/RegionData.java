package com.serpics.base.facade.data;

import com.serpics.core.facade.AbstractData;

public class RegionData  extends AbstractData{ 
	protected CountryData country;
	protected String  description;
	protected String name;
	public CountryData getCountry() {
		return country;
	}
	public void setCountry(CountryData country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
