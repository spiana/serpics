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
package com.serpics.core;

import java.security.Principal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.serpics.core.session.SessionContext;

public interface Engine<T extends SessionContext> extends ApplicationContextAware, BeanFactoryAware  {

	public T bind(String sessionId);

	public void unbind();

	public void disconnect(String sessionId);

	public void disconnect(T sessionContext);

	public T connect(String realm, String loginId,
			char[] password) throws SerpicsException;

	public T connect(String realm) throws SerpicsException;

	public T connect(String realm, Principal principal)
			throws SerpicsException;

	public T connect(T context,
			String loginId, char[] password) throws SerpicsException;

	public ApplicationContext getApplicationContext();

	public T getCurrentContext();

	public Object getService(String serviceName) throws BeansException;
}
