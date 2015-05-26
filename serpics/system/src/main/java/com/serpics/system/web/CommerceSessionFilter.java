package com.serpics.system.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;

public class CommerceSessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CommerceSessionFilter.class);

    @Autowired
    CommerceEngine ce;



    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
            final FilterChain chain) throws IOException, ServletException {

        if (logger.isDebugEnabled())
            logger.debug("CommerceSessionFilter called");

        
        final HttpServletRequest httpReq = (HttpServletRequest) req;
        final String id = (String) httpReq.getSession().getAttribute(
                WebCostant.SERPICS_SESSION);

        String realm = (String) httpReq.getSession().getAttribute(WebCostant.CURRENT_SESSION_STORE);
        if (realm == null)
            realm = "default-store";

        CommerceSessionContext context = null;
        if (id == null) {

            try {
                context = ce.connect(realm);

            } catch (final SerpicsException e) {
                throw new ServletException(e);
            }
            httpReq.getSession().setAttribute(WebCostant.SERPICS_SESSION, context.getSessionId());
        } else {

            if (logger.isDebugEnabled())
                logger.debug("found CommerceSessionID " + id
                        + " in HttpSession, trying to bind..");

            context = ce.bind(id);
        }

        if (context != null) {
            if (logger.isDebugEnabled())
                logger.debug("successfully bound commerce session");

        } else
        {
            // FIXME: CommerceSession expire before httpsession why ?
            httpReq.getSession().invalidate();
            throw new ServletException("bind commerce session failed !");


        }
        chain.doFilter(req, resp);
        ce.unbind(); // unbind from commerce Session
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
