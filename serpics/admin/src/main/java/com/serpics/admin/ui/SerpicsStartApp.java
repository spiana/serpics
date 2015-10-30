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
import com.serpics.vaadin.ui.I18nUtils;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.NavigatorMenuTree;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("tests-valo-dark")
@Component
@Scope("prototype")
@SpringUI
public class SerpicsStartApp extends UI {

	private static final long serialVersionUID = 690159590436610258L;

	Logger LOG = LoggerFactory.getLogger(SerpicsStartApp.class);

	@Autowired
	private transient CommerceEngine commerceEngine;

	@Autowired
	private NavigatorMenuTree navigatorMenuTree;

	private final TabSheet leftContentPanel = new TabSheet();
	@SuppressWarnings("rawtypes")
	private final Map<String, EntityComponent> activeComponent = new HashMap<String, EntityComponent>(
			0);

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
		layout.setMargin(true);
		layout.setSizeFull();
		setContent(layout);

		final HorizontalLayout topbar = new HorizontalLayout();
		topbar.setWidth("100%");
		topbar.setHeight("30px");
		
		final Label title = new Label("Serpics Admin Console");
		title.setWidth("80%");
		title.setHeight("100%");
		title.setStyleName("Apptitle");
		topbar.addComponent(title);
		
		final Label selectedStore = new Label(commerceEngine.getCurrentContext().getStoreRealm().getName());
		selectedStore.setWidth("20%");
		selectedStore.setHeight("100%");
		topbar.addComponent(selectedStore);
		
		layout.addComponent(topbar);
		
		final HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();


		final com.serpics.base.data.model.Locale locale = (com.serpics.base.data.model.Locale) commerceEngine
				.getCurrentContext().getLocale();

		Locale _locale = new Locale("it", "IT");
		if (locale != null) {
			_locale = new Locale(locale.getLanguage(), locale.getCountry());
		}

		getSession().setLocale(_locale);

		for (Object id : navigatorMenuTree.getItemIds()) {
			navigatorMenuTree.setItemCaption(id,
					I18nUtils.getMessage(id.toString(), id.toString()));
		}

		navigatorMenuTree.setWidth("150px");
		content.addComponent(navigatorMenuTree);

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
							I18nUtils.getMessage(itemid, itemid));
				else if (navigatorMenuTree.getClassComponent(itemid) != null) {
					addComponentByClass(
							navigatorMenuTree.getClassComponent(itemid),
							I18nUtils.getMessage(itemid, itemid));
				}
			}
		});

		leftContentPanel.setSizeFull();

		content.addComponent(leftContentPanel);
		content.setExpandRatio(leftContentPanel, 1);

		layout.addComponent(content);
		layout.setExpandRatio(content, 1);

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

	private void addComponentByClass(final String clazz, final String caption) {

		EntityComponent<?> _component = null;

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
			final Tab t = leftContentPanel.getTab(_component);
			if (t == null) {
				leftContentPanel.addTab(_component, caption);
				leftContentPanel.getTab(_component).setClosable(true);
			}
			leftContentPanel.setSelectedTab(_component);
		}

	}

	private EntityComponent<?> getComponent(final String name) {

		EntityComponent<?> _component = activeComponent.get(name);
		if (_component == null) {
			_component = (EntityComponent<?>) commerceEngine
					.getApplicationContext().getBean(name);
			activeComponent.put(name, _component);
		}
		return _component;

	}

}
