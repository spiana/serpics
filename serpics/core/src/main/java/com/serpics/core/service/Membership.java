package com.serpics.core.service;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserPrincipal;

public interface Membership {

	public UserPrincipal login(String username, char[] password) throws SerpicsException;

	public UserPrincipal createAnonymous();

	public StoreRealm fetchStoreByUUID(String storeUUID);
}
