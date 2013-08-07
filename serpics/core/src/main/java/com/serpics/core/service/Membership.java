package com.serpics.core.service;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserDetail;

public interface Membership {

	public UserDetail login(String username, char[] password) throws SerpicsException;

	public UserDetail createAnonymous();

	public StoreRealm fetchStoreByUUID(String storeUUID);
}
