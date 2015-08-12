package com.serpics.system.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
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
		if (EngineFactory.getCurrentApplicationContext() == null)
			EngineFactory.init();
		return  EngineFactory.getCurrentApplicationContext();
	}
}
