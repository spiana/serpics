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
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.NavigatorMenuTree;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("tests-valo-reindeer")
//@Theme("valo")
@Component
@Scope("prototype")
@SuppressWarnings("rawtypes")
@SpringUI
public class SerpicsStartApp extends UI {

	private static final long serialVersionUID = 690159590436610258L;

	Logger LOG = LoggerFactory.getLogger(SerpicsStartApp.class);

	@Autowired
	private transient CommerceEngine commerceEngine;

	@Autowired
	private NavigatorMenuTree navigatorMenuTree;

	private final TabSheet rightContentTabPanel = new TabSheet();
	@SuppressWarnings("rawtypes")
	private final Map<String, com.vaadin.ui.Component> activeComponent = new HashMap<String, com.vaadin.ui.Component>(0);

	@WebServlet(value = {"/*"}, asyncSupported = true)
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
		layout.setMargin(false);
		layout.setSizeFull();
		setContent(layout);

		final HorizontalLayout toolbarLayout = new HorizontalLayout();
		toolbarLayout.addStyleName("top-toolbar");
		 toolbarLayout.setWidth("100%");
         toolbarLayout.setSpacing(true);
         Label label = new Label("Tools");
         label.setSizeUndefined();
         toolbarLayout.addComponent(label);
         toolbarLayout.setExpandRatio(label, 1);
         toolbarLayout.setComponentAlignment(label,
                 Alignment.TOP_RIGHT);
		
		final HorizontalLayout menuTitle = new HorizontalLayout();
		menuTitle.addStyleName("valo-menu-title");

		final Label title = new Label("<b>Serpics Admin Console</b>" , ContentMode.HTML);
		menuTitle.addComponent(title);
		title.setSizeFull();
		title.setStyleName("h3");

		final HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		
		final com.serpics.base.data.model.Locale locale = (com.serpics.base.data.model.Locale) commerceEngine
				.getCurrentContext().getLocale();
		
		if (locale != null) {
			Locale _locale = new Locale(locale.getLanguage(), locale.getCountry());
			getSession().setLocale(_locale);
		}

		for (Object id : navigatorMenuTree.getItemIds()) {
			navigatorMenuTree.setItemCaption(id,
					I18nUtils.getMessage("smc.navigator."+id.toString(), id.toString()));
		}

		navigatorMenuTree.setWidth("100%");
		VerticalLayout leftPanel = new VerticalLayout();
		
		
		leftPanel.addStyleName("valo-menu");
		leftPanel.addComponent(menuTitle);
		
		final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final MenuItem settingsItem = settings.addItem("", new ThemeResource("../tests-valo/img/profile-pic-300px.jpg") , null);
        settingsItem.addItem("Edit Profile", null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", null);
        
        leftPanel.addComponent(settings);
		leftPanel.addComponent(navigatorMenuTree);
		
		content.addComponent(leftPanel);

		navigatorMenuTree.addItemClickListener(new ItemClickListener() {


			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {

				final String itemid = (String) event.getItemId();
				if (navigatorMenuTree.getBeanComponent(itemid) != null)
					addComponent(navigatorMenuTree.getBeanComponent(itemid),
							I18nUtils.getMessage("smc.navigator."+itemid, itemid));
				else if (navigatorMenuTree.getClassComponent(itemid) != null) {
					addComponentByClass(
							navigatorMenuTree.getClassComponent(itemid),
							I18nUtils.getMessage("smc.navigator."+itemid, itemid));
				}
			}
		});

		rightContentTabPanel.setSizeFull();
		rightContentTabPanel.addStyleName("valo-content");
		rightContentTabPanel.addStyleName("framed");

		VerticalLayout rightPanel = new VerticalLayout();
		rightPanel.setSizeFull();
		rightPanel.addComponents(toolbarLayout, rightContentTabPanel);
		rightPanel.setExpandRatio(toolbarLayout, 0.025F);
		rightPanel.setExpandRatio(rightContentTabPanel, 1F);
		
		
		content.addComponent(rightPanel);
		content.setExpandRatio(rightPanel, 5);
		content.setExpandRatio(leftPanel, 1);
//
		
		
		layout.addComponent(content);
		layout.setExpandRatio(content, 1);

	}

	private void addComponent(final String id, final String caption) {
		
		final com.vaadin.ui.Component _component = getComponent(id);

		final Tab t = rightContentTabPanel.getTab(_component);
		if (t == null) {
			rightContentTabPanel.addTab(_component, caption);
			rightContentTabPanel.getTab(_component).setClosable(true);
		}
		rightContentTabPanel.setSelectedTab(_component);
	}

	@SuppressWarnings({ "serial", "unchecked" })
	private void addComponentByClass(final String clazz, final String caption) {

		com.vaadin.ui.Component _component = null;

		_component = activeComponent.get(clazz);
		
		if (_component == null) {
			try {
				_component = new MasterTable(Class.forName(clazz)) {
				};
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		if (_component != null) {
			activeComponent.put(clazz, _component);
			final Tab t = rightContentTabPanel.getTab(_component);
			if (t == null) {
				rightContentTabPanel.addTab(_component, caption);
				rightContentTabPanel.getTab(_component).setClosable(true);
			}
			rightContentTabPanel.setSelectedTab(_component);
		}

	}

	private com.vaadin.ui.Component getComponent(final String name) {

		com.vaadin.ui.Component _component = activeComponent.get(name);
		if (_component == null) {
			_component = (com.vaadin.ui.Component) commerceEngine
					.getApplicationContext().getBean(name);
			activeComponent.put(name, _component);
		}
		return _component;

	}

}
