package com.serpics.gae.session;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.scope.SessionScopeContextHolder;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.session.SessionContext;
import com.serpics.core.session.SessionManager;

public class GaeContextSessionManager implements
		SessionManager , InitializingBean {
	private static transient Logger LOG = LoggerFactory.getLogger(GaeContextSessionManager.class);
	private MemcacheService mcs;
	

	@Override
	public SessionContext getSessionContext(String sessionId) {
		SessionContext context = (SessionContext) mcs.get(sessionId);
		if (context != null){
			LOG.info("found Session {} last access {}"  , sessionId , context.getLastAccess());
			context.setLastAccess(new Date());
			putSessionContext(sessionId, context);
		}
		return context;
	}

	@Override
	public void putSessionContext(String sessionId, SessionContext context) {
		mcs.put(sessionId, context, Expiration.byDeltaSeconds(1800));
	}

	@Override
	public SessionContext createSessionContext(StoreRealm realm) {
		final String sessionId = UUID.randomUUID().toString();
        final SessionScopeAttributes commerceScopeAttributes = new SessionScopeAttributes();
        commerceScopeAttributes.setConversationId(sessionId);

        SessionScopeContextHolder.setSessionScopeAttributes(commerceScopeAttributes);

        final CommerceSessionContext context = new CommerceSessionContext();
        context.setStoreRealm(realm);
        context.setSessionId(sessionId);
        context.setUserCookie(sessionId);
        context.setLastAccess(new Date());

        context.setCommerceScopeAttribute(commerceScopeAttributes);
        putSessionContext(sessionId, context);
        
        LOG.info("create new session with id [{}]", sessionId);
        return context;
	}

	@Override
	public void removeSessionContext(String sessionId) {
		mcs.delete(sessionId);
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mcs =MemcacheServiceFactory.getMemcacheService();
		
	}
	
	
}
