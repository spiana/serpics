package com.serpics.core.hook;

import javax.annotation.Resource;

import com.serpics.core.AbstractAutowiringFactoryBean;
import com.serpics.core.CommerceEngine;

public abstract class AbstractHookFactory<T> extends AbstractAutowiringFactoryBean<T> {

	@Resource
	CommerceEngine commerceEngine;

	@Override
	protected T doCreateInstance() {
		T hook = createHookInstance();
		((AbstractHook) hook).setSessionContext(commerceEngine.getCurrentContext());
		return hook;
	}

	public abstract T createHookInstance();

}
