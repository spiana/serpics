package com.serpics.core.session;

import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

import javax.annotation.Resource;

import com.serpics.core.scope.CommerceScopeAttributes;
import com.serpics.core.scope.CommerceScopeContextHolder;
import com.serpics.core.security.StoreRealm;

public class ContextSessionManager implements SessionManager {
	long sessionTimeout = 1800; // session timeout in seconds

	@Resource(name = "sessionContext")
	CommerceSessionContext context;

	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	private final Hashtable<String, SessionContext> sessionList = new Hashtable<String, SessionContext>();

	private String generateSessionID() {
		return UUID.randomUUID().toString();
	}

	@Override
	public SessionContext getSessionContext(String sessionId) {
		SessionContext sessionContext = sessionList.get(sessionId);
		if (sessionContext != null) {
			if ((new Date().getTime() - sessionContext.getLastAccess().getTime()) / 1000 > sessionTimeout) {
				sessionList.remove(sessionContext);
				sessionContext = null;
			} else
				sessionContext.setLastAccess(new Date());
		}
		if (sessionContext != null)
			CommerceScopeContextHolder.setThreadScopeAttributes(sessionContext.getCommerceScopeAttribute());

		return sessionContext;
	}

	@Override
	public SessionContext createSessionContext(StoreRealm realm) {
		String sessionId = generateSessionID();
		CommerceScopeAttributes commerceScopeAttributes = new CommerceScopeAttributes();
		commerceScopeAttributes.setConversationId(sessionId);
		context.setStoreRealm(realm);
		context.setSessionId(sessionId);
		context.setLastAccess(new Date());
		context.setCommerceScopeAttribute(commerceScopeAttributes);
		sessionList.put(sessionId, context);

		return context;
	}

	public void removeSessionContext(SessionContext context) {

	}

}
