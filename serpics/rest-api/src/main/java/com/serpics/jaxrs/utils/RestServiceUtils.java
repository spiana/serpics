package com.serpics.jaxrs.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.DistrictFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.DistrictData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.membership.facade.data.AddressData;

public class RestServiceUtils {
	
	@Autowired
	DistrictFacade districtFacade;
	
	@Autowired
	RegionFacade regionFacade;
	
	@Autowired
	CountryFacade countryFacade;

	public AddressData addressDataRequestToAddressData(AddressDataRequest addressDataRequest, AddressData addressData){
		BeanUtils.copyProperties(addressDataRequest, addressData,new String[]{"regionIsoCode","countryIso3Code","districtIsoCode"});
		if (addressDataRequest.getRegionIsoCode() != null){
			RegionData regionData = regionFacade.findRegionByCode(addressDataRequest.getRegionIsoCode());
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
		if (addressDataRequest.getDistrictIsoCode() != null){
			DistrictData districtData = districtFacade.findDistrictByCode(addressDataRequest.getDistrictIsoCode());
			if (districtData != null){
				addressData.setDistrict(districtData);
			}
		} else {
			addressData.setDistrict(null);
		}
		return addressData;
	}
	
}
