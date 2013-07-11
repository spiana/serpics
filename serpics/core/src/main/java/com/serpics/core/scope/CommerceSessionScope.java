package com.serpics.core.scope;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CommerceSessionScope implements Scope {

	@SuppressWarnings("rawtypes")
	@Override
	public Object get(String name, ObjectFactory factory) {
		Object result = null;

		Map<String, Object> hBeans = CommerceScopeContextHolder.getThreadScopeAttributes().getBeanMap();

		if (!hBeans.containsKey(name)) {
			result = factory.getObject();

			hBeans.put(name, result);
		} else {
			result = hBeans.get(name);
		}

		return result;
	}

	/**
	 * Removes bean from scope.
	 */
	@Override
	public Object remove(String name) {
		Object result = null;

		Map<String, Object> hBeans = CommerceScopeContextHolder.getThreadScopeAttributes().getBeanMap();

		if (hBeans.containsKey(name)) {
			result = hBeans.get(name);

			hBeans.remove(name);
		}

		return result;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		CommerceScopeContextHolder.getThreadScopeAttributes().registerRequestDestructionCallback(name, callback);
	}

	@Override
	public String getConversationId() {
		return CommerceScopeContextHolder.getThreadScopeAttributes().getConversationId();
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		Map<String, Object> hBeans = CommerceScopeContextHolder.getThreadScopeAttributes().getBeanMap();
		return hBeans.get(arg0);
	}

}