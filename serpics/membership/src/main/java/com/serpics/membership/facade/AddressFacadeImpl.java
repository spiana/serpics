package com.serpics.membership.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.CountryService;
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
	CountryService countryService;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public AbstractAddress buildAddress(AddressData source, AbstractAddress destination) {
		destination.setFirstname(source.getFirstname());
		destination.setLastname(source.getLastname());
		destination.setAddress1(source.getAddress1());
		destination.setStreetNumber(source.getStreetNumber());
		destination.setCity(source.getCity());
		destination.setCompany(source.getCompany());
		destination.setZipcode(source.getZipcode());
		destination.setVatcode(source.getVatcode());
		destination.setFax(source.getFax());

		if ((source.getRegion() != null) && (source.getRegion().getUuid() != null))
			destination.setRegion(regionService.findByUUID(source.getRegion().getUuid()));
		if ((source.getCountry() != null) && (source.getCountry().getUuid() != null))
			destination.setCountry(countryService.findByUUID(source.getCountry().getUuid()));
		return destination;
	}
	
	@Override
	public Address addressDataToAddress(AddressData addressData, User user){
		Address address = (Address) buildAddress(addressData, new Address());
		address.setMember(user);
		return addressRepository.saveAndFlush(address);
	}

}
