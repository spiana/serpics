package com.serpics.membership.facade;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.facade.data.CountryData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.facade.data.AddressData;
public class AddressPopulator implements Populator<AbstractAddress, AddressData>{
	private AbstractPopulatingConverter<Country, CountryData> countryConverter;
	@Override 
	public void populate(AbstractAddress source, AddressData target) {
		
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setNickname(source.getNickname());
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		target.setAddress1(source.getAddress1());
		target.setZipcode(source.getZipcode());
		target.setCity(source.getCity());
		//target.setRegion(source.getRegion());	
		target.setEmail(source.getEmail());
		
		if(source.getCountry() != null)
			target.setCountry(countryConverter.convert(source.getCountry()));
	}
	
	
	@Required
	public void setCountryConverter(
			AbstractPopulatingConverter<Country, CountryData> countryConverter) {
		this.countryConverter = countryConverter;
	}
	

}
