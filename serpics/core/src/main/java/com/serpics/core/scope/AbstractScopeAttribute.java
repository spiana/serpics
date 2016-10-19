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

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public abstract class AbstractScopeAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7000323362121580424L;

	final Logger logger = LoggerFactory.getLogger(AbstractScopeAttribute.class);

	protected final Map<String, Object> hBeans = new HashMap<String, Object>();
	protected final Map<String, Runnable> hRequestDestructionCallbacks = new LinkedHashMap<String, Runnable>();

	private String conversationId;

	/**
	 * Gets bean <code>Map</code>.
	 */
	protected final Map<String, Object> getBeanMap() {
		return hBeans;
	}

	/**
	 * Register the given callback as to be executed after request completion.
	 * 
	 * @param name
	 *            The name of the bean.
	 * @param callback
	 *            The callback of the bean to be executed for destruction.
	 */
	protected final void registerRequestDestructionCallback(String name, Runnable callback) {
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(callback, "Callback must not be null");

		hRequestDestructionCallbacks.put(name, callback);
	}

	/**
	 * Clears beans and processes all bean destruction callbacks.
	 */
	public final void clear() {
		processDestructionCallbacks();

		hBeans.clear();
	}

	/**
	 * Processes all bean destruction callbacks.
	 */
	private final void processDestructionCallbacks() {
		for (String name : hRequestDestructionCallbacks.keySet()) {
			Runnable callback = hRequestDestructionCallbacks.get(name);

			logger.debug("Performing destruction callback for '" + name + "' bean from conversation id :"
					+ conversationId);
			callback.run();
		}

		hRequestDestructionCallbacks.clear();
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

}
