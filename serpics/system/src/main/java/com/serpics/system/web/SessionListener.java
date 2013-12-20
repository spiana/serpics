package com.serpics.system.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.serpics.core.session.SessionManager;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(final HttpSessionEvent arg0) {

    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent arg0) {
        final ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0
                .getSession().getServletContext());
        final SessionManager sessionManager = applicationContext.getBean(SessionManager.class);

        final String sessionId = (String) arg0.getSession().getAttribute(WebCostant.SERPICS_SESSION);
        if (sessionId != null)
            sessionManager.removeSessionContext(sessionId);
    }

}
