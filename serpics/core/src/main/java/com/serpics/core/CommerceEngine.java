package com.serpics.core;

import java.security.Principal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.serpics.core.session.CommerceSessionContext;

public interface CommerceEngine extends ApplicationContextAware, BeanFactoryAware {

    public CommerceSessionContext bind(String sessionId);

    public void unbind();

    public void disconnect(String sessionId);

    public void disconnect(CommerceSessionContext sessionContext);

    public CommerceSessionContext connect(String realm, String loginId, char[] password) throws SerpicsException;

    public CommerceSessionContext connect(String realm) throws SerpicsException;

    public CommerceSessionContext connect(String realm, Principal principal) throws SerpicsException;

    public CommerceSessionContext connect(CommerceSessionContext context, String loginId, char[] password)
            throws SerpicsException;

    public ApplicationContext getApplicationContext();

    public CommerceSessionContext getCurrentContext();

    public Object getService(String serviceName) throws BeansException;

}
