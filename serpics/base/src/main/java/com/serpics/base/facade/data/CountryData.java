package com.serpics.base.facade.data;

import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.core.facade.AbstractData;


public class CountryData  extends AbstractData{ 
	
	protected Geocode geocode;
	protected MultilingualString  description;
	protected String iso2Code;
	protected String iso3Code;
	public Geocode getGeocode() {
		return geocode;
	}
	public void setGeocode(Geocode geocode) {
		this.geocode = geocode;
	}
	public MultilingualString getDescription() {
		return description;
	}
	public void setDescription(MultilingualString description) {
		this.description = description;
	}
	public String getIso2Code() {
		return iso2Code;
	}
	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}
	public String getIso3Code() {
		return iso3Code;
	}
	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}
	
	
	
	

}
