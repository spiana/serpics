package com.serpics.core.scope;

import javax.annotation.Resource;

import org.springframework.beans.factory.FactoryBean;

import com.serpics.core.hook.AbstractHook;
import com.serpics.core.session.SessionContext;

public class RealmFactoryBean implements FactoryBean<AbstractHook> {

	@Resource
	SessionContext sessionContext;

	@Override
	public AbstractHook getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getObjectType() {

		return AbstractHook.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
