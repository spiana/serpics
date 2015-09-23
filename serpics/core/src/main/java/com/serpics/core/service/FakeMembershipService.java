package com.serpics.core.service;

import java.security.Principal;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserDetail;


/*
 * This class is just to load apllicationContext in test environment
 * must be ovvrride with correct implementation on membership module
 */
public  class FakeMembershipService implements Membership{

	@Override
	public UserDetail login(String username, char[] password)
			throws SerpicsException {
		return null;
	}

	@Override
	public UserDetail createAnonymous() {
		return null;
	}

	@Override
	public StoreRealm fetchStoreByName(String storeName) {
		return null;
	}

	@Override
	public UserDetail connect(Principal principal) {
		return null;
	}

}
