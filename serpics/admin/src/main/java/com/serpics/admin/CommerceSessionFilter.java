package com.serpics.admin;

import java.io.IOException;

import javax.annotation.Resource;
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

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;


public class CommerceSessionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(CommerceSessionFilter.class);
	
	@Autowired
	CommerceEngine ce;
	
	@Resource(name="catalogService")
	CatalogService catService;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		if (logger.isDebugEnabled())
			logger.debug("CommerceSessionFilter called");
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		String id = (String) httpReq.getSession().getAttribute("serpics-session");
		try {
			if (id != null && !id.isEmpty()){
				CommerceSessionContext context = ce.bind(id);
			} else {
				CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
				context.setCatalog(catService.getCatalog("default-catalog"));
				httpReq.getSession().setAttribute("serpics-session", context.getSessionId());
			}
			
		} catch (SerpicsException e) {
			logger.error("Error establishing commerceSession in servlet filter", e);
			throw new ServletException(e);
		}
		
		chain.doFilter(req, resp);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
