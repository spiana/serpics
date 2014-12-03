package com.serpics.commerce.core;

import java.security.Principal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;

public interface CommerceEngine extends  Engine<CommerceSessionContext>{

}
