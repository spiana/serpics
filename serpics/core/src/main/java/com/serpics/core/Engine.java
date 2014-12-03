package com.serpics.core;

import java.security.Principal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.serpics.commerce.session.CommerceSessionContext;
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
