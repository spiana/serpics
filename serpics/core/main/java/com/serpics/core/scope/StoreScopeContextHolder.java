package com.serpics.core.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreScopeContextHolder {

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
			storeRealm = "null-store";
		}
		CommerceScopeAttributes scopeAttribute = storeScope.get(storeRealm);
		if (scopeAttribute == null) {
			scopeAttribute = new CommerceScopeAttributes();
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
