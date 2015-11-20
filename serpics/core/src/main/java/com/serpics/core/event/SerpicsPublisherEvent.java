package com.serpics.core.event;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;

@Service("serpicsPublisherEvent")
public class SerpicsPublisherEvent implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;
	
	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher paramApplicationEventPublisher) {
		this.publisher = paramApplicationEventPublisher;
	}

	public void publishSerpicsEvent(SerpicsEvent event){
		
		Assert.notNull(event,"Event object must be passed to mehod");
		
		CommerceSessionContext context = commerceEngine.getCurrentContext();
		Assert.notNull(context,"Current context not found");
		
		event.setRealm(context.getRealm());
		event.setSessionId(context.getSessionId());
		event.setStoreRealm(context.getStoreRealm());
		event.setCatalog(context.getCatalog());
		
		publisher.publishEvent(event);
	}
	
}
