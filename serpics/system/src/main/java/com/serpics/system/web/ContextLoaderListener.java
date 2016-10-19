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
package com.serpics.system.web;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.serpics.core.EngineFactory;

public class ContextLoaderListener extends
		org.springframework.web.context.ContextLoaderListener {
	private ContextLoader contextLoader;

	@Override
	public void contextInitialized(ServletContextEvent event) {

		if (contextLoader == null)
			this.contextLoader = this;
		 this.contextLoader.initWebApplicationContext(event.getServletContext());
	}
	
	@Override
	protected ApplicationContext loadParentContext(ServletContext servletContext) {
		if (EngineFactory.getCurrentApplicationContext() == null){
			URL modulesURL = null;
			try {
				modulesURL =  servletContext.getResource("WEB-INF/modules.xml");
			} catch (MalformedURLException e) {
			
			}
			if (modulesURL != null)
				EngineFactory.init(modulesURL);
			else
				EngineFactory.init();
		}
		return  EngineFactory.getCurrentApplicationContext();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (EngineFactory.getCurrentApplicationContext() != null)
			((ClassPathXmlApplicationContext) EngineFactory.getCurrentApplicationContext()).destroy();
		super.contextDestroyed(event);
	}
}
