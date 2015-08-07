package com.serpics.membership.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.membership.UserType;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;

public interface UserFacade {

	public Page<UserData> findAllUser(Pageable page);
	public UserData findUserById(Long userId);
	public Page<UserData> findUserByName(String name, Pageable page);
	public Page<UserData> findAllUserByUserType(UserType userType, Pageable page);
	
	public UserData getCurrentuser();
	
	public void registerUser(UserData user);
	public void updateUser(UserData user);
	public void updateContactAddress(AddressData address);
	
	public void addBillingAddress(AddressData address);
	public void updateBillingAddress(AddressData address);
	public void deleteBillingAddress();

	public void addDestinationAddress(AddressData address);
	public void updateDestinationAddress(AddressData address, String uuid);
	public void deleteDestinationAddress(String uuid);
	
}
