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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;

public class UserPopulator implements Populator<User, UserData> {

	private AbstractPopulatingConverter<AbstractAddress, AddressData> addressConverter;

	@Override
	public void populate(User source, UserData target) {
		
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		target.setLogonid(source.getName());
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		target.setPhone(source.getPhone());
		target.setEmail(source.getEmail());
		target.setUserType(source.getUserType());
		if (source.getPrimaryAddress() != null)
			target.setContactAddress(addressConverter.convert(source
					.getPrimaryAddress()));
		if (source.getBillingAddress() != null)
			target.setBillingAddress(addressConverter.convert(source
					.getBillingAddress()));

		if (source.getPermanentAddresses() != null) {
			List<AddressData> destinationAddress = new ArrayList<AddressData>();
			for (AbstractAddress address : source.getPermanentAddresses()) {
				destinationAddress.add(addressConverter.convert(address));
			}
			target.setDestinationAddress(destinationAddress);
		}
		
		
		
		
	}

	@Required
	public void setAddressConverter(
			AbstractPopulatingConverter<AbstractAddress, AddressData> addressConverter) {
		this.addressConverter = addressConverter;
	}

}
