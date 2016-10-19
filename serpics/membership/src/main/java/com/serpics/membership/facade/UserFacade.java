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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.core.SerpicsException;
import com.serpics.membership.UserType;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;

public interface UserFacade {

	public Page<UserData> findAllUser(Pageable page);
	public UserData findUserById(Long userId);
	public Page<UserData> findUserByName(String name, Pageable page);
	public Page<UserData> findAllUserByUserType(UserType userType, Pageable page);
	
	public UserData getCurrentuser();
	
	public void registerUser(UserData user) throws SerpicsException;
	public void updateUser(UserData user);
	public void updateContactAddress(AddressData address);
	
	public void addBillingAddress(AddressData address);
	public void updateBillingAddress(AddressData address);
	public void deleteBillingAddress();

	public void addDestinationAddress(AddressData address);
	public void updateDestinationAddress(AddressData address, Long id);
	public void deleteDestinationAddress(Long id);
	
}
