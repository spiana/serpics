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
