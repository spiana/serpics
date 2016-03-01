package com.serpics.base.facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;


public class CountryPopulator  implements Populator<Country, CountryData>{
	private AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConverter;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override
	public void populate(Country source, CountryData target) {
		
		target.setId(source.getCountriesId());
		target.setIso2Code(source.getIso2Code());
		target.setIso3Code(source.getIso3Code());
		if (source.getGeocode() != null){
			target.setGeocode(geocodeConverter.convert(source.getGeocode()));
		}
		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		target.setUuid(source.getUuid()); 
	}
	
	@Required
	public void setGeocodeConverter(
			AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConverter) {
		this.geocodeConverter = geocodeConverter;
	}
	
}

