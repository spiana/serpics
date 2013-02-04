package com.serpics.core.hook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
	public void performAction(String action , Object[] args){
		Class<?>[] p  = new Class<?>[args.length];
		for (int x = 0 ; x < args.length ; x++) {
			p[x] = args[x].getClass();
		}
		
		try {
			Method m = this.getClass().getMethod(action , p);
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
	
	
}
