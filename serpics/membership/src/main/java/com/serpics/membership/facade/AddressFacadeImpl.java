/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.membership.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.CountryService;
import com.serpics.base.services.DistrictService;
import com.serpics.base.services.RegionService;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.AddressRepository;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("addressFacade")
@Transactional(readOnly = true)
public class AddressFacadeImpl implements AddressFacade{
	
	@Autowired
	RegionService regionService;
	
	@Autowired
	DistrictService districtService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public AbstractAddress buildAddress(AddressData source, AbstractAddress destination) {

		destination.setFirstname(source.getFirstname());
		destination.setLastname(source.getLastname());
		destination.setAddress1(source.getAddress1());
		destination.setAddress2(source.getAddress2());
		destination.setAddress3(source.getAddress3());
		destination.setStreetNumber(source.getStreetNumber());
		destination.setCity(source.getCity());
		destination.setCompany(source.getCompany());
		destination.setZipcode(source.getZipcode());
		destination.setVatcode(source.getVatcode());
		destination.setFax(source.getFax());
		destination.setEmail(source.getEmail());
		destination.setMobile(source.getMobile());
		destination.setPhone(source.getPhone());

		if ((source.getRegion() != null) && (source.getRegion().getIsoCode() != null)){
			destination.setRegion(regionService.getRegionByCode(source.getRegion().getIsoCode()));
		} else {
			destination.setRegion(null);
		}
		if ((source.getDistrict() != null) && (source.getDistrict().getIsoCode() != null)){
			destination.setDistrict(districtService.getDistrictByCode(source.getDistrict().getIsoCode()));
		} else {
			destination.setRegion(null);
		}
		if ((source.getCountry() != null) && (source.getCountry().getIso3Code() != null)){
			destination.setCountry(countryService.getCountryByIso3Code(source.getCountry().getIso3Code()));
		} else {
			destination.setCountry(null);
		}
		return destination;
	}
	
	@Override
	public Address addressDataToAddress(AddressData addressData, User user){
		Address address = (Address) buildAddress(addressData, new Address());
		//address.setMember(user);
		return addressRepository.saveAndFlush(address);
	}

}
