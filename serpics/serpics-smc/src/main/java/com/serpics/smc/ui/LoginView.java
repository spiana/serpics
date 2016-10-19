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
package com.serpics.smc.ui;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.vaadin.annotations.Theme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("tests-valo-light")
@org.springframework.stereotype.Component
@Scope("prototype")
public class LoginView extends CustomComponent {

	private static Logger logger = LoggerFactory.getLogger(LoginView.class);
	
	private static final long serialVersionUID = 2192899815277460850L;

	private final AuthenticationManager authenticationManager;

	/**
	 * An event fired by a {@link LoginView} to indicate that a user has been
	 * successfully authenticated.
	 * 
	 * @author Petter Holmström
	 */
	public static class LoginEvent extends Event {

		private static final long serialVersionUID = -8875211687130316896L;

		private final Authentication authentication;

		/**
		 * Creates a new <code>LoginEvent</code>.
		 * 
		 * @param source
		 *            the login view that fired the event.
		 * @param authentication
		 *            the authenticated object.
		 */
		public LoginEvent(LoginView source, Authentication authentication) {
			super(source);
			this.authentication = authentication;
		}

		/**
		 * Gets the fully authenticated {@link Authentication}-instance with
		 * credentials.
		 * 
		 * @return the authentication object (never <code>null</code>).
		 */
		public Authentication getAuthentication() {
			return authentication;
		}
	}

	/**
	 * Creates a new <code>LoginView</code>.
	 * 
	 * @param application
	 *            the application that owns the view.
	 * @param authenticationManager
	 *            the authentication manager to use (must not be
	 *            <code>null</code>).
	 */
	public LoginView(
			AuthenticationManager authenticationManager) {
		assert authenticationManager != null : "authenticationManager must not be null";
		this.authenticationManager = authenticationManager;
		init();
	}

	@SuppressWarnings("serial")
	protected void init() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setCaption("login.title");
		loginPanel.setSpacing(true);

		final TextField username = new TextField("userName");
		username.setWidth("100%");
		loginPanel.addComponent(username);

		final TextField password = new TextField("login.password");
		password.addStyleName("password");
		password.setWidth("100%");
		loginPanel.addComponent(password);

		final Button loginButton = new Button("login.button");
		loginButton.setStyleName("primary");
		// TODO Make it possible to submit the form by pressing <Enter> in any
		// of the text fields
		loginPanel.addComponent(loginButton);
		loginPanel.setComponentAlignment(
				loginButton, Alignment.MIDDLE_RIGHT);
		
		loginButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				final Authentication auth = new UsernamePasswordAuthenticationToken(
						username.getValue(), password.getValue());
				try {
					if (logger.isDebugEnabled()) {
						logger.debug("Attempting authentication for user '"
								+ auth.getName() + "'");
					}
					Authentication returned = getAuthenticationManager()
							.authenticate(auth);
					if (logger.isDebugEnabled()) {
						logger.debug("Authentication for user '"
								+ auth.getName() + "' succeeded");
					}
					fireEvent(new LoginEvent(LoginView.this, returned));
				} catch (BadCredentialsException e) {
					if (logger.isDebugEnabled()) {
						logger.debug("Bad credentials for user '"
								+ auth.getName() + "'", e);
					}
//					getWindow().showNotification(
//							getApplication().getMessage(
//									"login.badCredentials.title"),
//							getApplication().getMessage(
//									"login.badCredentials.descr"),
//							Notification.TYPE_WARNING_MESSAGE);
				} catch (DisabledException e) {
					if (logger.isDebugEnabled()) {
						logger.debug("Account disabled for user '"
								+ auth.getName() + "'", e);
					}
//					getWindow()
//							.showNotification(
//									getApplication().getMessage(
//											"login.disabled.title"),
//									getApplication().getMessage(
//											"login.disabled.descr"),
//									Notification.TYPE_WARNING_MESSAGE);
				} catch (LockedException e) {
					if (logger.isDebugEnabled()) {
						logger.debug("Account locked for user '"
								+ auth.getName() + "'", e);
					}
//					getWindow().showNotification(
//							getApplication().getMessage("login.locked.title"),
//							getApplication().getMessage("login.locked.descr"),
//							Notification.TYPE_WARNING_MESSAGE);
				} catch (Exception e) {
					if (logger.isErrorEnabled()) {
						logger
								.error("Error while attempting authentication for user '"
										+ auth.getName() + "'");
					}
				//	ExceptionUtils.handleException(getWindow(), e);
				}
			}
		});

		
		
		loginPanel.setWidth("300px");

		final HorizontalLayout viewLayout = new HorizontalLayout();
		viewLayout.addComponent(loginPanel);
		viewLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		viewLayout.setSizeFull();
		viewLayout.setMargin(true);

		setCompositionRoot(viewLayout);
		setSizeFull();
	}

	/**
	 * Gets the authentication manager that should be used when attempting
	 * authentication.
	 * 
	 * @return the authentication manager (never <code>null</code>).
	 */
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
}
