package com.serpics.core.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreScopeContextHolder {

	private static transient String DEFAULT_STORE_REALM = "default-store";

	private static final ThreadLocal<String> currentStoreRealm = new ThreadLocal<String>();

	private static final Map<ClassLoader, StoreScopeMap> currentContextPerThread = new ConcurrentHashMap<ClassLoader, StoreScopeMap>(
			100);

	public static StoreScopeAttributes getCommerceScopeAttributes() {
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
		return currentStoreRealm.get();
	}
}
