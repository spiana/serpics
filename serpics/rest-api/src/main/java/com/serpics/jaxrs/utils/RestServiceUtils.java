package com.serpics.jaxrs.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.membership.facade.data.AddressData;

public class RestServiceUtils {
	
	@Autowired
	RegionFacade regionFacade;
	
	@Autowired
	CountryFacade countryFacade;

	public AddressData addressDataRequestToAddressData(AddressDataRequest addressDataRequest, AddressData addressData){
		BeanUtils.copyProperties(addressDataRequest, addressData,new String[]{"regionName","countryIso3Code"});
		if (addressDataRequest.getRegionName() != null){
			RegionData regionData = regionFacade.findRegionByName(addressDataRequest.getRegionName());
			if (regionData != null){
				addressData.setRegion(regionData);
			}
		} else {
			addressData.setRegion(null);
		}
		if (addressDataRequest.getCountryIso3Code() != null){
			CountryData countryData = countryFacade.findCountryByIso3Code(addressDataRequest.getCountryIso3Code());
			if (countryData != null){
				addressData.setCountry(countryData);
			}
		} else {
			addressData.setCountry(null);
		}
		return addressData;
	}
	
}
