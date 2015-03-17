package com.serpics.membership.facade;

import com.serpics.core.facade.AbstractConverter;
import com.serpics.membership.persistence.AbstractAddress;

public abstract class AddressConverter extends AbstractConverter<AbstractAddress, AddressData> {

	@Override
	public void populate(AbstractAddress source, AddressData target) {
		// TODO Auto-generated method stub
	
	
	}

	@Override
	public AddressData createTarget() {
		return new AddressData();
	}

}
