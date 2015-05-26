package com.serpics.system.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

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
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.services.UserRegService;
import com.serpics.system.services.UserDetailsService;
import com.serpics.system.web.WebCostant;

public class AuthenticationHandler implements AuthenticationSuccessHandler {


    @Autowired
    private CommerceEngine commerceEngine;

    @Autowired
    private MembersRoleRepository membersRoleRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRegService userRegService;

    @Autowired
    private CatalogService catalogService;

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
            context.setCatalog(catalogService.findByCode("default-catalog"));
            final UsersReg user = ((UsersReg) context.getUserPrincipal());
            final Locale locale = user.getLocale();

            if (locale != null) {
                context.setLocale(locale);
            }

            user.setLastLogin(new Date());
            user.setLastVisit(user.getLastLogin());
            userRegService.update(user);

        } catch (final SerpicsException e) {
            authentication.setAuthenticated(false);
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }

}
