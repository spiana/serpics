package com.serpics.system.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.repositories.MembersRoleRepository;
import com.serpics.system.services.UserDetailsService;
import com.serpics.system.web.WebCostant;

public class AuthenticationHandler implements AuthenticationSuccessHandler {


    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    MembersRoleRepository membersRoleRepository;
    @Autowired
    UserDetailsService userDetailsService;

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

            final SessionContext context = commerceEngine.connect(selectedRealm, (Principal) authentication);
            // setting credentials for selected store
            userDetailsService.setCredentials(authentication);
            request.getSession().setAttribute(WebCostant.SERPICS_SESSION, context.getSessionId());

        } catch (final SerpicsException e) {
            authentication.setAuthenticated(false);
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }

}