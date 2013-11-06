package com.serpics.membership.facade;


import com.serpics.core.facade.AbstractObjectConverter;
import com.serpics.membership.persistence.User;

public class UserConverter extends AbstractObjectConverter<User, UserData> {

	@Override
	public void populate(User source, UserData target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserData createTarget() {
		return new UserData();
	}

}
