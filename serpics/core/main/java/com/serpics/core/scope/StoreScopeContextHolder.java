package com.serpics.core.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreScopeContextHolder {

	private static transient String DEFAULT_STORE_REALM = "default-store";

	private static final ThreadLocal<String> currentStoreRealm = new ThreadLocal<String>();

	private static final Map<ClassLoader, StoreScopeAttribute> currentContextPerThread = new ConcurrentHashMap<ClassLoader, StoreScopeAttribute>(
			100);

	public static CommerceScopeAttributes getCommerceScopeAttributes() {
		StoreScopeAttribute storeScope = currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
		if (storeScope == null) {
			storeScope = new StoreScopeAttribute();
			setStoreScopeAttributes(storeScope);
		}
		String storeRealm = currentStoreRealm.get();
		if (storeRealm == null) {
			storeRealm = DEFAULT_STORE_REALM;
		}
		CommerceScopeAttributes scopeAttribute = storeScope.get(storeRealm);
		if (scopeAttribute == null) {
			scopeAttribute = new CommerceScopeAttributes();
			scopeAttribute.setConversationId(storeRealm);
			storeScope.put(storeRealm, scopeAttribute);
		}

		return scopeAttribute;

	}

	public static void setStoreScopeAttributes(StoreScopeAttribute storeScopeAttributes) {
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), storeScopeAttributes);
	}

	public static void setCurrentStoreRealm(String storeRealm) {
		currentStoreRealm.set(storeRealm);
	}
}
