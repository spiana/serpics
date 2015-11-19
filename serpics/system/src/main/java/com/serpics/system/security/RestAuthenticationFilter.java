package com.serpics.system.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.system.web.WebCostant;

public class RestAuthenticationFilter extends GenericFilterBean {

	Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Resource(name="authenticationManager")
	AuthenticationManager authManager;

	@Resource(name="engine")
	CommerceEngine commerceEngine;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String ssid = httpRequest.getHeader(WebCostant.SSID_SERPICS_TOKEN);
		
		logger.debug("Call RestAuthenticationFilter: " + ssid);
		if(ssid!=null){
			CommerceSessionContext context=commerceEngine.bind(ssid);
			if(context!=null){
				UserDetail userDetails = context.getUserPrincipal();
				if(userDetails instanceof UsersReg){
					UsersReg user = (UsersReg)userDetails;
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getLogonid(),
							user.getPassword());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) httpRequest));
					// set the authentication into the SecurityContext
					SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authentication));
				}
			}
		}
		
		// continue thru the filter chain
		chain.doFilter(request, response);
	}

}