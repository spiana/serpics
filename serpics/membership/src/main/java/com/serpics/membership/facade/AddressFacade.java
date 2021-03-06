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

import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.User;
import com.serpics.membership.facade.data.AddressData;

public interface AddressFacade {

	public AbstractAddress buildAddress(AddressData source, AbstractAddress destination);

	public Address addressDataToAddress(AddressData addressData, User user);
	
}
