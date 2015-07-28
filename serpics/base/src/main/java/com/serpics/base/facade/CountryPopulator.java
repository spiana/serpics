package com.serpics.base.facade;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;


public class CountryPopulator  implements Populator<Country, CountryData>{
	private AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConverter;
	
	@Override
	public void populate(Country source, CountryData target) {
		
		target.setIso2Code(source.getIso2Code());
		target.setIso3Code(source.getIso3Code());
		target.setGeocode(geocodeConverter.convert(source.getGeocode()));
		target.setDescription(source.getDescription().getText("it"));
		target.setUuid(source.getUuid());
	}
	
	@Required
	public void setGeocodeConverter(
			AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConverter) {
		this.geocodeConverter = geocodeConverter;
	}
}

