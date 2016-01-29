package com.serpics.base.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class RegionPopulator implements Populator<Region, RegionData>{
	private AbstractPopulatingConverter<Country, CountryData> countryConverter;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override
	public void populate(Region source, RegionData target) {
		
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription().getText("it"));
		if(source.getCountry() != null)
			target.setCountry(countryConverter.convert(source.getCountry())); 

		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		//if(source.getCountry() != null)
			//target.setCountry(countryConverter.convert(source.getCountry())); 
		
	}
	
	@Required
	public void setCountryConverter(
			AbstractPopulatingConverter<Country, CountryData> countryConverter) {
		this.countryConverter = countryConverter;
	}
}
