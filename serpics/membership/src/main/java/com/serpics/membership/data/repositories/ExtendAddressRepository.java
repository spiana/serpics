package com.serpics.membership.data.repositories;

import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;

public interface ExtendAddressRepository {

	public Address clone(BillingAddress address);
	public Address clone(PermanentAddress address);
	
}
