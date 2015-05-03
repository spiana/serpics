package com.serpics.membership.strategies;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.data.model.Store;

public interface MembershipStrategy {

	public UserDetail login(Store store, String userName, char[] password) throws SerpicsException;
}
