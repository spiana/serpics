package com.serpics.core.scope;

import javax.annotation.Resource;

import com.serpics.core.AbstractAutowiringFactoryBean;
import com.serpics.core.hook.AbstractHook;
import com.serpics.core.session.SessionContext;

public class RealmFactoryBean<T> extends AbstractAutowiringFactoryBean<T> {

	@Resource
	SessionContext sessionContext;

	@Override
	protected T doCreateInstance() {

		return null;

	}

	@Override
	public Class<?> getObjectType() {

		return AbstractHook.class;
	}

}
