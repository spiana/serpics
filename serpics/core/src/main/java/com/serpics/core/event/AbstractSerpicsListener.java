package com.serpics.core.event;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;

public abstract class AbstractSerpicsListener<E extends SerpicsEvent> implements ApplicationListener<E> {

	@Autowired
	CommerceEngine commerceEngine;

	@Override
	public void onApplicationEvent(E serpicsEvent) {
		Assert.notNull(serpicsEvent, "Event object null");

		CommerceSessionContext context = commerceEngine.getCurrentContext();
		if (context != null && StringUtils.equalsIgnoreCase(context.getSessionId(), serpicsEvent.getSessionId())
				&& StringUtils.equalsIgnoreCase(context.getRealm(), serpicsEvent.getRealm())) 
		{
			handleEvent(serpicsEvent);
		}
	}

	public abstract void handleEvent(E arg0);
}
