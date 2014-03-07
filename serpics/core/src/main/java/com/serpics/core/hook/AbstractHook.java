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

    public void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = (CommerceSessionContext) sessionContext;
    }

    public void performAction(final String action, final Object[] args) {
        final Class<?>[] p = new Class<?>[args.length];
        for (int x = 0; x < args.length; x++) {
            p[x] = args[x].getClass();
        }

        try {
            final Method m = this.getClass().getMethod(action, p);
            try {
                m.invoke(this, args);
            } catch (final IllegalArgumentException e) {
            } catch (final IllegalAccessException e) {
            } catch (final InvocationTargetException e) {
            }
        } catch (final SecurityException e) {
        } catch (final NoSuchMethodException e) {
        }
    }

}
