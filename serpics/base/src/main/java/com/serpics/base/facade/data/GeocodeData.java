package com.serpics.base.facade.data;

import com.serpics.core.facade.AbstractData;

public class GeocodeData extends AbstractData{ 
	protected String name;
	protected String code;
	protected String description;
	protected CountryData country;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public CountryData getCountry() {
		return country;
	}
	public void setCountry(CountryData country) {
		this.country = country;
	}
}
