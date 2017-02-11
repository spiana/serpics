/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
import com.serpics.membership.services.BaseService;

public class CommerceSessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CommerceSessionFilter.class);

    @Autowired
    CommerceEngine ce;
    
    @Autowired 
    BaseService baseService;

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
            final FilterChain chain) throws IOException, ServletException {

        if (logger.isDebugEnabled())
            logger.debug("CommerceSessionFilter called");

        final HttpServletRequest httpReq = (HttpServletRequest) req;
        final String id = (String) httpReq.getSession().getAttribute(WebCostant.SERPICS_SESSION);
        
        CommerceSessionContext context = null;
        
        String request_store =((HttpServletRequest)req).getParameter("store");
        
        if (id == null) {
        	String realm = null;
            String pathInfo = ((HttpServletRequest)req).getRequestURI();
         
        	logger.info("current URL {} !", pathInfo);
        	logger.info("request store is {}" , request_store);
//        	String[] _temp = pathInfo.split(";");
//        	if(_temp.length==2)
//        		realm = _temp[1];
//        	
            if(realm == null)
    	         realm = (String) httpReq.getSession().getAttribute(WebCostant.CURRENT_SESSION_STORE);
            
            if (realm == null)
            	if (request_store != null)
            		realm = request_store;	
            	else	
            		realm = "default-store";

            try {
                context = ce.connect(realm);
                httpReq.getSession().setAttribute(WebCostant.CURRENT_SESSION_STORE, realm);
                httpReq.getSession().setAttribute(WebCostant.SERPICS_SESSION, context.getSessionId());
            } catch (final SerpicsException e) {
                throw new ServletException(e);
            }
            
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
