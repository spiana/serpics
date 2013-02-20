package com.serpics.core.hook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.serpics.core.session.CommerceSessionContext;
import com.serpics.core.session.SessionContext;

public abstract class AbstractHook {

	protected CommerceSessionContext sessionContext;

	public CommerceSessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = (CommerceSessionContext) sessionContext;
	}

	public void performAction(String action, Object[] args) {
		Class<?>[] p = new Class<?>[args.length];
		for (int x = 0; x < args.length; x++) {
			p[x] = args[x].getClass();
		}

		try {
			Method m = this.getClass().getMethod(action, p);
			try {
				m.invoke(this, args);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
	}

	private BeanDefinition scanClassLoader() {
		Class returnType = null;
		try {
			Method m = getClass().getDeclaredMethod("getObject");
			returnType = m.getReturnType();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Hook.class));
		Set<BeanDefinition> beans = scanner.findCandidateComponents("");
		for (BeanDefinition beanDefinition : beans) {
			// beanDefinition.get
			Hook c = beanDefinition.getClass().getAnnotation(Hook.class);
			if (c.type().equals(returnType.getName()))
				;
		}

		return null;
	}
}
