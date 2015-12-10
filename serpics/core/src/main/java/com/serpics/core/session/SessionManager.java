package com.serpics.core.session;

import com.serpics.core.security.StoreRealm;

public interface SessionManager {

	public SessionContext getSessionContext(String sessionId);
	public void putSessionContext(String sessionId , SessionContext context);
	

	public SessionContext createSessionContext(StoreRealm realm , String sessionId);
	
	public void removeSessionContext(String sessionId);
}
