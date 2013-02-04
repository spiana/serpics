package com.serpics.core.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.serpics.core.hook.AbstractHook;
import com.serpics.core.hook.HookMappingException;
import com.serpics.core.hook.HooksMap;
import com.serpics.core.session.SessionContext;

public class HookLookupImpl implements HookLookup, BeanFactoryAware {
	BeanFactory beanFactory;

	Map<String, HooksMap> realmhooks = new HashMap<String, HooksMap>();

	public void setRealmhooks(Map<String, HooksMap> realmhooks) {
		this.realmhooks = realmhooks;
	}

	@Override
	public AbstractHook fetchHook(SessionContext context, String hookName) {
		HooksMap hooks = realmhooks.get(context.getRealm());
		if (hooks == null)
			hooks = realmhooks.get("default");

		String beanName = hooks.get(hookName);
		if (beanName == null)
			throw new RuntimeException(new HookMappingException());

		AbstractHook c = (AbstractHook) this.beanFactory.getBean(beanName);
		c.setSessionContext(context);

		return c;
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.beanFactory = arg0;

	}

}
