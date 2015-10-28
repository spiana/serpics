package com.serpics.system.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.services.BaseService;

public class CommerceRequestFilter implements Filter {
//    private static final Logger logger = LoggerFactory.getLogger(CommerceRequestFilter.class);

	@Resource
	BaseService baseService;
	
    @Autowired
    CommerceEngine ce;

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
            final FilterChain chain) throws IOException, ServletException {
    	
    	if (!baseService.isInitialized())
	    	baseService.initIstance();
    	
    	
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
