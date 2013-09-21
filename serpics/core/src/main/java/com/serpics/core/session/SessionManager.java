package com.serpics.core.session;

import com.serpics.core.security.StoreRealm;

public interface SessionManager {

	public SessionContext getSessionContext(String sessionId);

	public SessionContext createSessionContext(StoreRealm realm);
	
	public void removeSessionContext(String sessionId);
}
