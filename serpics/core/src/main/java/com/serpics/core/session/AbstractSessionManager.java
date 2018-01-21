/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.core.session;

import java.util.Date;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.scope.SessionScopeContextHolder;
import com.serpics.core.security.Realm;

public abstract class AbstractSessionManager implements SessionManager {
    private static transient Logger logger = LoggerFactory.getLogger(AbstractSessionManager.class);

    protected final Hashtable<String, SessionContext> sessionList = new Hashtable<String, SessionContext>();
    
    private GenerateSessionIdStrategy generateSessionIdStrategy ;

    protected String generateSessionID(String realm) {
        return getGenerateSessionIdStrategy().generate(realm);
    }
    
    protected SessionContext makeSessionContext(String sessionId , Realm realm){
    	   Assert.notNull(sessionId, "sessionId can non be null !");
    	  	final SessionScopeAttributes commerceScopeAttributes = new SessionScopeAttributes();

    	  	commerceScopeAttributes.setConversationId(sessionId);
    	  
    	  	SessionScopeContextHolder.setSessionScopeAttributes(commerceScopeAttributes);
           final SessionContext context = new SessionContext();
           context.setRealm(realm);
           context.setSessionId(sessionId);
           context.setLastAccess(new Date());

           context.setCommerceScopeAttribute(commerceScopeAttributes);

           sessionList.put(sessionId, context);
           logger.info("create new session with id [{}]", sessionId);
           return context;
    	
    }

    @Override
    public SessionContext createSessionContext(final Realm realm , String sessionId) {
    	if (sessionId == null){
    		sessionId = generateSessionID(realm.getName());
    	}
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
        

        if (sessionContext != null) {
        	if (logger.isDebugEnabled()) {
                logger.debug("session realm  [{}]", sessionContext.getRealm());
                logger.debug("session user  [{}]", sessionContext.getUserPrincipal().getName());
                logger.debug("session last access  [{}]", sessionContext.getLastAccess());
            }
            sessionContext.setLastAccess(new Date());
            SessionScopeContextHolder.setSessionScopeAttributes(sessionContext.getCommerceScopeAttribute());
        }

        return sessionContext;
    }
    
    @Override
    public void putSessionContext(String sessionId, SessionContext context) {
    	sessionList.put(sessionId, context);
    }

	public GenerateSessionIdStrategy getGenerateSessionIdStrategy() {
		return generateSessionIdStrategy;
	}

	@Required
	public void setGenerateSessionIdStrategy(
			GenerateSessionIdStrategy generateSessionIdStrategy) {
		this.generateSessionIdStrategy = generateSessionIdStrategy;
	}
}
