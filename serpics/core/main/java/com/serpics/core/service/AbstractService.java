package com.serpics.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.hook.AbstractHook;
import com.serpics.core.session.CommerceSessionContext;

@Transactional(readOnly = true)
public abstract class AbstractService {
	@Autowired(required = true)
	HookLookup hookLookup;

	public void setHookLookup(HookLookup hookLookup) {
		this.hookLookup = hookLookup;
	}

	@Autowired(required = true)
	CommerceEngine commerceEngine;

	public CommerceEngine getCommerceEngine() {
		return commerceEngine;
	}

	public void setCommerceEngine(CommerceEngine commerceEngine) {
		this.commerceEngine = commerceEngine;
	}

	protected AbstractHook getHook(String hookName, CommerceSessionContext context) {
		return hookLookup.fetchHook(context, hookName);
	}

	protected AbstractHook getHook(String hookName) {
		return hookLookup.fetchHook(commerceEngine.getCurrentContext(), hookName);
	}

	protected CommerceSessionContext getCurrentContext() {
		return commerceEngine.getCurrentContext();
	}

}
