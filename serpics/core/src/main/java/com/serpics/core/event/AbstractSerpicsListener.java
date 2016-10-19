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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.stereotype.StoreEvent;


public abstract class AbstractSerpicsListener<E extends SerpicsEvent> implements ApplicationListener<E> {

	@Autowired
	CommerceEngine commerceEngine;

	@Override
	public void onApplicationEvent(E serpicsEvent) {
		Assert.notNull(serpicsEvent, "Event object null");

		StoreEvent annotation = getClass().getAnnotation(StoreEvent.class);
		if (annotation != null){
			String[] stores =annotation.stores();
			for (String string : stores) {
				if(string.equals(serpicsEvent.getStoreRealm()) || string.equals("default-store")){
						CommerceSessionContext context = commerceEngine.bind(serpicsEvent.getSessionId());
						if (context != null){
							handleEvent(serpicsEvent);
						}
				}
			}
		}

	}

	public abstract void handleEvent(E arg0);
}
