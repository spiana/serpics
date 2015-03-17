package com.serpics.membership.facade;


import com.serpics.core.facade.AbstractConverter;
import com.serpics.membership.persistence.User;

public abstract class UserConverter extends AbstractConverter<User, UserData> {

	@Override
	public void populate(User source, UserData target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserData createTarget() {
		return new UserData();
	}

}
