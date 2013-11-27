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
import com.serpics.membership.services.BaseService;

public class CommerceSessionFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(CommerceSessionFilter.class);

	@Autowired
	CommerceEngine ce;

	@Resource(name = "catalogService")
	CatalogService catService;

	@Autowired
	BaseService baseService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		if (logger.isDebugEnabled())
			logger.debug("CommerceSessionFilter called");

		if (!baseService.isInitialized()){
			baseService.initIstance();
			Catalog catalog = new Catalog();
			catalog.setCode("default-catalog");
			catService.createCatalog(catalog);
		}
		

		HttpServletRequest httpReq = (HttpServletRequest) req;
		String id = (String) httpReq.getSession().getAttribute(
				"serpics-session");

		try {
			if (id != null && !id.isEmpty()) {
				if (logger.isDebugEnabled())
					logger.debug("found CommerceSessionID " + id
							+ " in HttpSession, trying to bind..");
				CommerceSessionContext context = ce.bind(id);
				if (context != null) {
					if (logger.isDebugEnabled())
						logger.debug("successfully bound commerce session");
				} else {

				}
			} else {
				CommerceSessionContext context = ce.connect("default-store",
						"superuser", "admin".toCharArray());
				context.setCatalog(catService.getCatalog("default-catalog"));
				httpReq.getSession().setAttribute("serpics-session",
						context.getSessionId());
			}

		} catch (Exception e) {
			logger.error(
					"Error establishing commerceSession in servlet filter", e);

			// httpReq.getSession().removeAttribute("serpics-session");

			CommerceSessionContext context;
			try {
				context = ce.connect("default-store", "superuser",
						"admin".toCharArray());
				context.setCatalog(catService.getCatalog("default-catalog"));
				httpReq.getSession().setAttribute("serpics-session",
						context.getSessionId());
			} catch (Exception e1) {
				logger.error(
						"Error establishing commerceSession in servlet filter",
						e1);
			}

			// throw new ServletException(e);
		}

		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
