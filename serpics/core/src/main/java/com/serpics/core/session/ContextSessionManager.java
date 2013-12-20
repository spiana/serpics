package com.serpics.core.session;

import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.serpics.core.scope.SessionScopeContextHolder;

public class ContextSessionManager extends AbstractSessionManager implements SessionManager, InitializingBean,
DisposableBean {
    private static transient Logger logger = LoggerFactory
            .getLogger(ContextSessionManager.class);
    long sessionTimeout = 1800; // session timeout in seconds

    ClearSessionExpired se;

    private class ClearSessionExpired extends Thread {

        public ClearSessionExpired() {
            setName("clearSessionExpired");
        }

        @Override
        public void run() {
            logger.info("started !");
            while (!isInterrupted()) {
                try {
                    sleep(60000);
                } catch (final InterruptedException e) {
                    break;
                }

                synchronized (sessionList) {
                    final Iterator<String> i = sessionList.keySet().iterator();
                    while (i.hasNext()) {
                        final SessionContext sessionContext = sessionList.get(i
                                .next());
                        if (sessionContext != null
                                && (new Date().getTime() - sessionContext
                                        .getLastAccess().getTime()) / 1000 > sessionTimeout)
                            i.remove();
                    }
                }

            }
            logger.info("stopped !");
        }
    }

    public void setSessionTimeout(final long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    @Override
    public SessionContext getSessionContext(final String sessionId) {
        SessionContext sessionContext = sessionList.get(sessionId);
        if (sessionContext != null) {
            if ((new Date().getTime() - sessionContext.getLastAccess().getTime()) / 1000 > sessionTimeout) {
                logger.info("session [{}] expired !", sessionId);
                sessionList.remove(sessionContext);
                sessionContext = null;
            } else {
                logger.info("found session for sessionid [{}] !", sessionId);
                if (logger.isDebugEnabled()) {
                    logger.debug("session realm  [{}]", sessionContext.getRealm());
                    logger.debug("session user  [{}]", sessionContext.getUserPrincipal().getName());
                    logger.debug("session last access  [{}]", sessionContext.getLastAccess());
                }
                sessionContext.setLastAccess(new Date());
            }
        }
        if (sessionContext != null)
            SessionScopeContextHolder.setSessionScopeAttributes(sessionContext.getCommerceScopeAttribute());

        return sessionContext;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        se = new ClearSessionExpired();
        se.start();

    }

    @Override
    public void destroy() throws Exception {
        se.interrupt();
        se.join();
    }

}
