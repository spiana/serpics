package com.serpics.membership.facade;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.facade.data.AddressData;
public class AddressPopulator implements Populator<AbstractAddress, AddressData>{
	private AbstractPopulatingConverter<Country, CountryData> countryConverter;
	private AbstractPopulatingConverter<Region, RegionData> regionConverter;
	@Override 
	public void populate(AbstractAddress source, AddressData target) {
		
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setNickname(source.getNickname());
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		
		target.setAddress1(source.getAddress1());
		target.setAddress2(source.getAddress2());
		target.setAddress3(source.getAddress3());
		target.setStreetNumber(source.getStreetNumber());
		target.setZipcode(source.getZipcode());
		target.setCity(source.getCity());
			
		target.setEmail(source.getEmail());
		
		target.setField1(source.getField1());
		target.setField2(source.getField2());
		
		if(source.getRegion() != null){
			target.setRegion(regionConverter.convert(source.getRegion()));
		} else {
			target.setRegion(null);
		}
		if(source.getCountry() != null){
			target.setCountry(countryConverter.convert(source.getCountry()));
		} else {
			target.setCountry(null);
		}
		
		
	}
	
	
	@Required
	public void setCountryConverter(
			AbstractPopulatingConverter<Country, CountryData> countryConverter) {
		this.countryConverter = countryConverter;
	}
	
	@Required
	public void setRegionConverter(
			AbstractPopulatingConverter<Region, RegionData> regionConverter) {
		this.regionConverter = regionConverter;
	}

}
