package com.serpics.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.serpics.core.session.CommerceSessionContext;

public interface CommerceEngine extends ApplicationContextAware, BeanFactoryAware {

	public CommerceSessionContext bind(String sessionId);

	public CommerceSessionContext connect(String realm, String loginId, char[] password) throws SerpicsException;

	public CommerceSessionContext connect(String realm) throws SerpicsException;

	public CommerceSessionContext connect(CommerceSessionContext context, String loginId, char[] password)
			throws SerpicsException;

	public ApplicationContext getApplicationContext();

	public CommerceSessionContext getCurrentContext();

	public void setCurrentContext(CommerceSessionContext context);

	public Object getService(String serviceName) throws BeansException;

}
