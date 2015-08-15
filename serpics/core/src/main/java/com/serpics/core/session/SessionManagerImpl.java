package com.serpics.core.session;

import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.security.StoreRealm;

public class SessionManagerImpl implements SessionManager {
	long sessionTimeout = 1800; // session timeout in seconds

	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	private final Hashtable<String, SessionContext> sessionList = new Hashtable<String, SessionContext>();

	private String generateSessionID() {
		return UUID.randomUUID().toString();
	}

	@Override
	public SessionContext getSessionContext(String sessionId) {
		SessionContext ctx = sessionList.get(sessionId);

		if (ctx == null)
			return null;

		if ((new Date().getTime() - ctx.getLastAccess().getTime()) / 1000 > sessionTimeout) {
			sessionList.remove(ctx);
			ctx = null;
		} else
			ctx.setLastAccess(new Date());

		return ctx;
	}

	@Override
	public SessionContext createSessionContext(StoreRealm realm) {
		String sessionId = generateSessionID();
		CommerceSessionContext context = new CommerceSessionContext(realm);
		context.setSessionId(sessionId);
		context.setUserCookie(sessionId);
		context.setLastAccess(new Date());
		sessionList.put(sessionId, context);
		return context;
	}

	@Override
	public void removeSessionContext(String sessionId) {
			sessionList.remove(sessionId);
	}

	@Override
	public void putSessionContext(String sessionId, SessionContext context) {
		// TODO Auto-generated method stub
		
	}

}
