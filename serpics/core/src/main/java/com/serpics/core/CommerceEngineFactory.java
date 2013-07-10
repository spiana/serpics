package com.serpics.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.restlet.Component;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CommerceEngineFactory {

	private static final Map<ClassLoader, ApplicationContext> currentContextPerThread = new ConcurrentHashMap<ClassLoader, ApplicationContext>(
			1);

	public static void init(String xmlConfigFile) {
		setApplicationContext(new FileSystemXmlApplicationContext(xmlConfigFile));
		// CommerceEngine ce = (CommerceEngine)
		// getCurrentApplicationContext().getBean(CommerceEngine.class);
		// ce.setApplicationContext(getCurrentApplicationContext());
		try {
			Component c = (Component) getCurrentApplicationContext().getBean("resletEngine");
			if (c != null)
				try {
					c.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (BeansException e) {

		}
	}

	public final static void setApplicationContext(ApplicationContext appContext) {
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), appContext);
	}

	public final static ApplicationContext getCurrentApplicationContext() {
		return currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
	}

	public final static CommerceEngine getCommerceEngine() {
		return getCurrentApplicationContext().getBean(CommerceEngine.class);
	}

}
