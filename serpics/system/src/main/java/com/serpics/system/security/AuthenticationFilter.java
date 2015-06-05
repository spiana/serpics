package com.serpics.system.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.serpics.base.data.model.Locale;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.system.services.UserDetailsService;
import com.serpics.system.web.WebCostant;

public class AuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    CommerceEngine commerceEngine;

 
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CatalogService catalogService;

    @Override
    protected void onUnsuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException failed) throws IOException {

        super.onUnsuccessfulAuthentication(request, response, failed);
    }

    private AuthenticationFilter(final AuthenticationManager authenticationManager,
            final AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);

    }

    @Override
    protected void onSuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws IOException {
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
            context.setCatalog(catalogService.findByCode("default-catalog"));
            final UsersReg user = ((UsersReg) context.getUserPrincipal());
            final Locale locale = user.getLocale();

            if (locale != null) {
                context.setLocale(locale);
            }

            userDetailsService.updateLastVisit(user);
    
        } catch (final SerpicsException e) {
            authentication.setAuthenticated(false);
            throw new IOException(e);
        }
        super.onSuccessfulAuthentication(request, response, authentication);
    }
}
