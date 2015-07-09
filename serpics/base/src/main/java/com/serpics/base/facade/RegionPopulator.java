package com.serpics.base.facade;


import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class RegionPopulator implements Populator<Region, RegionData>{
	private AbstractPopulatingConverter<Country, CountryData> countryConverter;
	
	@Override
	public void populate(Region source, RegionData target) {
		target.setName(source.getName());
		
		if(source.getCountry() != null)
			target.setCountry(countryConverter.convert(source.getCountry())); 
		
	}
	
	@Required
	public void setCountryConverter(
			AbstractPopulatingConverter<Country, CountryData> countryConverter) {
		this.countryConverter = countryConverter;
	}
}
