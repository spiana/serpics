package com.serpics.jaxrs.data.converter;


import com.serpics.core.facade.Populator;
import com.serpics.jaxrs.data.UserDataRequest;
import com.serpics.membership.facade.data.UserData;

public class UserDataRequestConverter implements Populator<UserDataRequest, UserData>{

	@Override
	public void populate(UserDataRequest source, UserData target) {
		
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		target.setEmail(source.getEmail());
		target.setLogonid(source.getLogonid());
		target.setPassword(source.getPassword());
		
	}
}