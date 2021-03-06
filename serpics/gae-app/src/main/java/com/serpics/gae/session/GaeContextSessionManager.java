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
package com.serpics.gae.session;

import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.serpics.base.commerce.security.StoreRealm;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.scope.SessionScopeContextHolder;
import com.serpics.core.security.Realm;
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
	public SessionContext createSessionContext(Realm realm , String sessionId) {
		 Assert.isInstanceOf(StoreRealm.class, realm);
		 
		if (sessionId == null){
			sessionId = UUID.randomUUID().toString();
			String prefix = Base64.encodeBase64String(realm.getName().getBytes());
			sessionId = prefix+"-"+sessionId;
		}
		String[] tokens = sessionId.split("-");
		if(tokens.length != 6)
			throw new RuntimeException("invalid sessiondId format !");

		final SessionScopeAttributes commerceScopeAttributes = new SessionScopeAttributes();
        commerceScopeAttributes.setConversationId(sessionId);

        SessionScopeContextHolder.setSessionScopeAttributes(commerceScopeAttributes);

        final CommerceSessionContext context = new CommerceSessionContext();
        context.setStoreRealm((StoreRealm)realm);
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

	@Override
	public Hashtable<String, SessionContext> getSesssionList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
