package com.serpics.membership.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.membership.UserType;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;

public interface UserFacade {

	public UserData getCurrentuser();
	public Page<UserData> findAllUser(Pageable page);
	public UserData findUserById(Long userId);
	public Page<UserData> findUserByLogonid(String logonid, Pageable page);
	public Page<UserData> findUserByName(String name, Pageable page);
	public Page<UserData> findAllUserByUserType(UserType userType, Pageable page);
	
	public void registerUser(UserData user);
	
	public void addAddress(AddressData address);
	public void addBillingAddress(AddressData address);
	public void updateContactAddress(AddressData address);
	public void updateBillingAddress(AddressData address);
	
	public void updateAddress(String addressUUID, AddressData address);
	public void updateBillingAddress(String addressUUID, AddressData address);
	public void updatePermanentAddress(String addressUUID, AddressData address);
	
	public void updateUser(String userUUID , UserData user);
	public void updateUser(UserData user);

}
