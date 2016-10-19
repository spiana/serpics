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
package com.serpics.core.scope;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CommerceSessionScope implements Scope {

	@SuppressWarnings("rawtypes")
	@Override
	public Object get(String name, ObjectFactory factory) {
		Object result = null;

		Map<String, Object> hBeans = SessionScopeContextHolder.currentSessionScopeAttributes().getBeanMap();

		if (!hBeans.containsKey(name)) {
			result = factory.getObject();

			hBeans.put(name, result);
		} else {
			result = hBeans.get(name);
		}

		return result;
	}

	/**
	 * Removes bean from scope.
	 */
	@Override
	public Object remove(String name) {
		Object result = null;

		Map<String, Object> hBeans = SessionScopeContextHolder.currentSessionScopeAttributes().getBeanMap();

		if (hBeans.containsKey(name)) {
			result = hBeans.get(name);

			hBeans.remove(name);
		}

		return result;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		SessionScopeContextHolder.getSessionScopeAttributes().registerRequestDestructionCallback(name, callback);
	}

	@Override
	public String getConversationId() {
		return SessionScopeContextHolder.getSessionScopeAttributes().getConversationId();
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		Map<String, Object> hBeans = SessionScopeContextHolder.getSessionScopeAttributes().getBeanMap();
		return hBeans.get(arg0);
	}

}
