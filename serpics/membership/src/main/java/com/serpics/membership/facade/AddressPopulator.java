package com.serpics.membership.facade;

import com.serpics.core.facade.Populator;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.facade.data.AddressData;
public class AddressPopulator implements Populator<AbstractAddress, AddressData>{

	@Override
	public void populate(AbstractAddress source, AddressData target) {
		
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		target.setAddress1(source.getAddress1());
		target.setZipcode(source.getZipcode());
		target.setCity(source.getCity());
		target.setRegion(source.getRegion());
		target.setEmail(source.getEmail());
		
	}

}
