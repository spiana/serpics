package com.serpics.admin.ui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.vaadin.ui.EntityComponent;
import com.serpics.vaadin.ui.NavGeneratoFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("tests-valo-facebook")
@Component
@Scope("prototype")
public class SerpicsStartApp extends UI {

	private static final long serialVersionUID = -5966946454650068735L;

	Logger LOG = LoggerFactory.getLogger(SerpicsStartApp.class);

	@Autowired
	private transient CommerceEngine commerceEngine;

	@Autowired
	private NavGeneratoFactory navGeneratoFactory;

	private final TabSheet leftContentPanel = new TabSheet();
	@SuppressWarnings("rawtypes")
	private final Map<String, EntityComponent> activeComponent = new HashMap<String, EntityComponent>(0);

	@WebServlet(value = "/*", asyncSupported = true)
	public static class Servlet extends SpringVaadinServlet {
	}

	@WebListener
	public static class MyContextLoaderListener extends ContextLoader {
	}

	@Configuration
	@EnableVaadin
	public static class MyConfiguration {
	}

	@Override
	protected void init(final VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		setContent(layout);
		final Label title = new Label("Serpics Admin Console");
		title.setWidth("100%");
		title.setHeight("30px");
		title.setStyleName("Apptitle");
		layout.addComponent(title);

		final HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();

		final com.serpics.base.data.model.Locale locale = (com.serpics.base.data.model.Locale) commerceEngine
				.getCurrentContext().getLocale();

		Locale _locale = new Locale("it", "IT");
		if (locale != null) {
			_locale = new Locale(locale.getLanguage(), locale.getCountry());
		}

		getSession().setLocale(_locale);
		final Tree menu = populateMenu();
		menu.setWidth("150px");
		content.addComponent(menu);

		menu.addItemClickListener(new ItemClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {
				final String itemid = (String) event.getItemId();
				LOG.info("item id : {}", itemid);
				if (itemid.equalsIgnoreCase("users")) {
					addComponent("userTableEditor", "user");
				} else if (itemid.equalsIgnoreCase("groups")) {
					addComponent("membergroupTableEditor", "groups");
				} else if (itemid.equalsIgnoreCase("relation")) {
					addComponent("membergroupRelTable", "user2grouprelation");
				} else if (itemid.equalsIgnoreCase("userReg")) {
					addComponent("userRegTableEditor", "userReg");
				} else if (itemid.equalsIgnoreCase("category")) {
					addComponent("categoryTable", "category");
				} else if (itemid.equalsIgnoreCase("product")) {
					addComponent("productTable", "product");
				} else if (itemid.equalsIgnoreCase("country")) {
					addComponent("countryTable", "country");
				} else if (itemid.equalsIgnoreCase("geocode")) {
					addComponent("geocodeTable", "geocode");
				} else if (itemid.equalsIgnoreCase("brand")) {
					addComponent("brandTable", "brand");
				} else if (itemid.equalsIgnoreCase("currency")) {
					addComponent("currencyTable", "currency");
				} else if (itemid.equalsIgnoreCase("featureModel")) {
					addComponent("featureModelTable", "featureModel");
				}
			}
		});

		leftContentPanel.setSizeFull();

		content.addComponent(leftContentPanel);
		content.setExpandRatio(leftContentPanel, 1);

		layout.addComponent(content);
		layout.setExpandRatio(content, 1);

	}

	/**
	 * 
	 * @return a tree generated from xml
	 */
	private Tree populateMenu() {
		final Tree customizableNav = navGeneratoFactory.generateNavigator();
		return customizableNav;
	}

	private void addComponent(final String id, final String caption) {
		final EntityComponent<?> _component = getComponent(id);

		final Tab t = leftContentPanel.getTab(_component);
		if (t == null) {
			leftContentPanel.addTab(_component, caption);
			leftContentPanel.getTab(_component).setClosable(true);
		}
		leftContentPanel.setSelectedTab(_component);
	}

	private EntityComponent<?> getComponent(final String name) {

		EntityComponent<?> _component = activeComponent.get(name);
		if (_component == null) {
			_component = (EntityComponent<?>) commerceEngine.getApplicationContext().getBean(name);
			// _component.init();

			activeComponent.put(name, _component);
		}

		return _component;

	}

}
