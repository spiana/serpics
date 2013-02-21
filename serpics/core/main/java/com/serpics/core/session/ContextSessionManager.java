package com.serpics.core.session;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.serpics.core.scope.CommerceScopeAttributes;
import com.serpics.core.scope.CommerceScopeContextHolder;
import com.serpics.core.security.StoreRealm;

public class ContextSessionManager implements SessionManager, InitializingBean {
	private static transient Logger logger = LoggerFactory.getLogger(ContextSessionManager.class);
	long sessionTimeout = 1800; // session timeout in seconds

	private class ClearSessionExpired extends Thread {

		public ClearSessionExpired() {
			setName("clearSessionExpired");
		}

		@Override
		public void run() {
			logger.info("started !");
			while (!isInterrupted()) {
				synchronized (sessionList) {
					Iterator<String> i = sessionList.keySet().iterator();
					while (i.hasNext()) {
						getSessionContext(i.next());
					}
				}
				try {
					sleep(60000);
				} catch (InterruptedException e) {
				}

			}
			logger.info("stooped !");
		}

	}

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
				logger.info("session [{}] expired !", sessionId);
				sessionList.remove(sessionContext);
				sessionContext = null;
			} else {
				logger.info("found session for sessionid [{}] !");
				if (logger.isDebugEnabled()) {
					logger.debug("session realm  [{}]", sessionContext.getRealm());
					logger.debug("session user  [{}]", sessionContext.getUserPrincipal().getName());
					logger.debug("session last access  [{}]", sessionContext.getLastAccess());
				}
				sessionContext.setLastAccess(new Date());
			}
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
		CommerceScopeContextHolder.setThreadScopeAttributes(commerceScopeAttributes);
		context.setStoreRealm(realm);
		context.setSessionId(sessionId);
		context.setLastAccess(new Date());
		context.setCommerceScopeAttribute(commerceScopeAttributes);
		sessionList.put(sessionId, context);
		logger.info("create new session with if [{}]", sessionId);
		return context;
	}

	public void removeSessionContext(SessionContext context) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ClearSessionExpired se = new ClearSessionExpired();
		se.start();

	}

}
