package com.serpics.core.hook;

import com.serpics.core.AbstractAutowiringFactoryBean;

public class HookFactoryBean extends AbstractAutowiringFactoryBean<AbstractHook> {

	@Override
	protected AbstractHook doCreateInstance() {
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return AbstractHook.class;
	}

}
