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
package com.serpics.core.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreScopeContextHolder {

	private static transient String DEFAULT_STORE_REALM = "default-store";

	private static final ThreadLocal<String> currentStoreRealm = new ThreadLocal<String>();

	private static final Map<ClassLoader, StoreScopeMap> currentContextPerThread = new ConcurrentHashMap<ClassLoader, StoreScopeMap>(
			100);

	public static StoreScopeAttributes getStoreScopeAttributes() {
		StoreScopeMap storeScope = currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
		if (storeScope == null) {
			storeScope = new StoreScopeMap();
			setStoreScopeAttributes(storeScope);
		}
		String storeRealm = currentStoreRealm.get();
		if (storeRealm == null) {
			storeRealm = DEFAULT_STORE_REALM;
			currentStoreRealm.set(storeRealm);
		}

		StoreScopeAttributes scopeAttribute = storeScope.get(storeRealm);
		if (scopeAttribute == null) {
			scopeAttribute = new StoreScopeAttributes();
			scopeAttribute.setConversationId(storeRealm);
			storeScope.put(storeRealm, scopeAttribute);
		}

		return scopeAttribute;

	}

	public static void setStoreScopeAttributes(StoreScopeMap storeScopeAttributes) {
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), storeScopeAttributes);
	}

	public static void setCurrentStoreRealm(String storeRealm) {
		currentStoreRealm.set(storeRealm);
	}

	public static String getCurrentStoreRealm() {
		String realm = currentStoreRealm.get();
//		if (realm == null)
//			realm = "default-store";
		return realm;
	}
}
