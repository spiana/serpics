package com.serpics.membership.facade;

import com.serpics.core.facade.AbstractObjectConverter;
import com.serpics.membership.persistence.AbstractAddress;

public class AddressConverter extends AbstractObjectConverter<AbstractAddress, AddressData> {

	@Override
	public void populate(AbstractAddress source, AddressData target) {
		// TODO Auto-generated method stub
	
	
	}

	@Override
	public AddressData createTarget() {
		return new AddressData();
	}

}
