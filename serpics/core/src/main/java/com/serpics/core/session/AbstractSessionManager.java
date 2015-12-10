package com.serpics.core.session;

import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.scope.SessionScopeContextHolder;
import com.serpics.core.security.StoreRealm;

public abstract class AbstractSessionManager implements SessionManager {
    private static transient Logger logger = LoggerFactory.getLogger(AbstractSessionManager.class);

    protected final Hashtable<String, SessionContext> sessionList = new Hashtable<String, SessionContext>();

    private String generateSessionID() {
        return UUID.randomUUID().toString();
    }
    
    private SessionContext makeSessionContext(String sessionId , StoreRealm realm){
    	   Assert.notNull(sessionId, "sessionId can non be null !");
    	   
    	  	final SessionScopeAttributes commerceScopeAttributes = new SessionScopeAttributes();
    	   commerceScopeAttributes.setConversationId(sessionId);
    	   
    	   SessionScopeContextHolder.setSessionScopeAttributes(commerceScopeAttributes);

           
           final CommerceSessionContext context = new CommerceSessionContext();
           context.setStoreRealm(realm);
           context.setSessionId(sessionId);
           context.setUserCookie(sessionId);
           context.setLastAccess(new Date());

           context.setCommerceScopeAttribute(commerceScopeAttributes);

           sessionList.put(sessionId, context);
           logger.info("create new session with id [{}]", sessionId);
           return context;
    	
    }

    @Override
    public SessionContext createSessionContext(final StoreRealm realm , String sessionId) {
    	if (sessionId == null){
    		sessionId = generateSessionID();
    		String token_prefix = Base64.encodeBase64String(realm.getName().getBytes());
    		sessionId = token_prefix + "-"+ sessionId;
    	}
    	String[] tokens = sessionId.split("-");
    	if (tokens.length != 6)
    		throw new RuntimeException("invalid sessionId format !");
    	
        return makeSessionContext(sessionId , realm);
    }

    @Override
    public void removeSessionContext(final String sessionId) {
        synchronized (sessionList) {
            sessionList.remove(sessionId);
        }
    }

    @Override
    public SessionContext getSessionContext(final String sessionId) {
        final SessionContext sessionContext = sessionList.get(sessionId);
        logger.info("found session for sessionid [{}] !", sessionId);
        if (logger.isDebugEnabled()) {
            logger.debug("session realm  [{}]", sessionContext.getRealm());
            logger.debug("session user  [{}]", sessionContext.getUserPrincipal().getName());
            logger.debug("session last access  [{}]", sessionContext.getLastAccess());
        }

        if (sessionContext != null) {
            sessionContext.setLastAccess(new Date());
            SessionScopeContextHolder.setSessionScopeAttributes(sessionContext.getCommerceScopeAttribute());
        }

        return sessionContext;
    }
    
    @Override
    public void putSessionContext(String sessionId, SessionContext context) {
    	sessionList.put(sessionId, context);
    }
}
