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
package com.serpics.system.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.serpics.base.data.model.Locale;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.system.services.UserDetailsService;
import com.serpics.system.web.WebCostant;

public class AuthenticationHandler implements AuthenticationSuccessHandler {


    @Autowired
    private CommerceEngine commerceEngine;

//    @Autowired
//    private MembersRoleRepository membersRoleRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CatalogService catalogService;

    private String redirectURL = "/";
    
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication)
                    throws IOException, ServletException {
        String selectedRealm = (String) request.getSession().getAttribute(WebCostant.CURRENT_SESSION_STORE);
        final String sessionId = (String) request.getSession().getAttribute(WebCostant.SERPICS_SESSION);

        if (selectedRealm == null)
            selectedRealm = "default-store";

        try {
            // disconnect old session if exist !
            if (sessionId != null)
                commerceEngine.disconnect(sessionId);

            final CommerceSessionContext context = commerceEngine.connect(selectedRealm, (Principal) authentication);
            // setting credentials for selected store
            userDetailsService.setCredentials(authentication);
            request.getSession().setAttribute(WebCostant.SERPICS_SESSION, context.getSessionId());
            catalogService.setDefaultCatalog("");
            final UsersReg user = ((UsersReg) context.getUserPrincipal());
            final Locale locale = user.getLocale();

            if (locale != null) {
                context.setLocale(locale);
            }
           userDetailsService.updateLastVisit(user);
        } catch (final SerpicsException e) {
            authentication.setAuthenticated(false);
            throw new ServletException(e);
        }
      
        response.sendRedirect(request.getContextPath() + redirectURL);
        return;
    }

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
    
}
