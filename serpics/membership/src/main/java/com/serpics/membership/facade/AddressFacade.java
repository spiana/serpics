package com.serpics.membership.facade;

import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.User;
import com.serpics.membership.facade.data.AddressData;

public interface AddressFacade {

	public AbstractAddress buildAddress(AddressData source, AbstractAddress destination);

	public Address addressDataToAddress(AddressData addressData, User user);
	
}
