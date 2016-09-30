package com.serpics.smc.ui;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

//@Theme("tests-valo-light")
@Theme("valo")
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
			mainView.builContent();
			setContent(mainView);
		
			
			
		
	}

	
}
