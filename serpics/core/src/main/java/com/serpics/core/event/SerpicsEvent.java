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
package com.serpics.core.event;

import org.springframework.context.ApplicationEvent;

import com.serpics.commerce.core.security.StoreRealm;
import com.serpics.core.data.model.Catalog;

public abstract class SerpicsEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4819807126083042202L;
	
	private String sessionId;
    private String realm;
	private StoreRealm storeRealm;
	private Catalog catalog;
	
	public SerpicsEvent(Object source) {
		super(source);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public StoreRealm getStoreRealm() {
		return storeRealm;
	}

	public void setStoreRealm(StoreRealm storeRealm) {
		this.storeRealm = storeRealm;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
}
