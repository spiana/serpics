package com.serpics.membership.hooks;

import com.serpics.core.SerpicsException;
import com.serpics.core.hook.Hook;
import com.serpics.core.security.UserPrincipal;
import com.serpics.membership.persistence.Store;

@Hook
public interface MembershipHook {

	public UserPrincipal login(Store store, String userName, char[] password) throws SerpicsException;
}
