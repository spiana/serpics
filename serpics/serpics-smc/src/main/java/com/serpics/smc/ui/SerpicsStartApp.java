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

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

//@Theme("tests-valo-light")
@Theme("tests-valo")
@Component
@Scope("prototype")
@SuppressWarnings("rawtypes")
@SpringUI
public class SerpicsStartApp extends UI {

	private static final long serialVersionUID = 690159590436610258L;

	Logger LOG = LoggerFactory.getLogger(SerpicsStartApp.class);

	@Resource
	MainView mainView;
	
//	@WebServlet(value = {"/smc/*" , "/VAADIN/*"}, asyncSupported = true)
//	public static class Servlet extends SpringVaadinServlet {
//	}
//	@Configuration
//	@EnableVaadin
//	public static class MyConfiguration {
//	}

	@Override
	protected void init(final VaadinRequest request) {
		Cookie[] cookies =request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("smc-theme")){
				String theme = cookie.getValue();
				setTheme(theme);
				// reset cookie expire
				 Cookie new_cookie = new Cookie("smc-theme", theme);
	              new_cookie.setPath("/");
	              new_cookie.setMaxAge(30*24*60*60);
	              VaadinService.getCurrentResponse().addCookie(new_cookie);
				break;
			}
		}
		
			
		mainView.builContent();
		setContent(mainView);
		
			
			
	}

	
}
