package com.serpics.membership.hooks;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.data.model.Store;
import com.serpics.stereotype.Hook;

@Hook("membership")
public interface MembershipHook {

	public UserDetail login(Store store, String userName, char[] password) throws SerpicsException;
}
