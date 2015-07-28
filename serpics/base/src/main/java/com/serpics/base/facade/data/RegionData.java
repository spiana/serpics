package com.serpics.base.facade.data;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.core.facade.AbstractData;

public class RegionData  extends AbstractData{ 
	protected CountryData country;
	protected MultilingualString  description;
	protected String name;
	public CountryData getCountry() {
		return country;
	}
	public void setCountry(CountryData country) {
		this.country = country;
	}
	public MultilingualString getDescription() {
		return description;
	}
	public void setDescription(MultilingualString description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
