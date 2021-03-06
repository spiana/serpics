/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.core.event;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;

@Service("serpicsPublisherEvent")
public class SerpicsPublisherEvent implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;
	
	@Resource
	Engine<SessionContext> engine;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher paramApplicationEventPublisher) {
		this.publisher = paramApplicationEventPublisher;
	}

	public void publishSerpicsEvent(SerpicsEvent event){
		
		Assert.notNull(event,"Event object must be passed to mehod");
		
		SessionContext context = engine.getCurrentContext();
		Assert.notNull(context,"Current context not found");
		
		event.setContext(context);
		
		publisher.publishEvent(event);
	}
	
}
