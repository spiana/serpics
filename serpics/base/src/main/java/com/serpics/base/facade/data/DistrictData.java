package com.serpics.base.facade.data;

import com.serpics.core.facade.AbstractData;

public class DistrictData  extends AbstractData{ 
	protected CountryData country;
	protected String  description;
	protected String isoCode;
	protected  RegionData region;
	
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
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public RegionData getRegion() {
		return region;
	}
	public void setRegion(RegionData region) {
		this.region = region;
	}
	
	
}
