package com.serpics.core;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractAutowiringFactoryBean<T> extends AbstractFactoryBean<T> implements
		ApplicationContextAware {

	protected ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	protected final T createInstance() throws Exception {
		final T instance = doCreateInstance();
		if (instance != null) {
			applicationContext.getAutowireCapableBeanFactory().autowireBean(instance);
		}
		// the following code is to generate a Proxy instead of a real class
		
//		ProxyFactory pf = new ProxyFactory(instance);
//		pf.setProxyTargetClass(true);
//		pf.setFrozen(true);
//		return (T) pf.getProxy();
	return instance;
	}

	/**
	 * Create the bean instance.
	 * 
	 * @see #createInstance()
	 */
	protected abstract T doCreateInstance();

	@Override
	public boolean isSingleton() {
		return false;
	}

}