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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.smc.ui.memeship.UserRegEditorComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.NavigatorMenuTree;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Component("mainView")
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class MainView extends CustomComponent {

	
	private static final long serialVersionUID = 690159590436610258L;

	Logger LOG = LoggerFactory.getLogger(MainView.class);

	@Autowired
	private transient CommerceEngine commerceEngine;
	
	@Autowired
	private UserRegEditorComponent userProfile;

	@Autowired
	private NavigatorMenuTree navigatorMenuTree;
	
	private final Label storeLabel = new Label();

	private final TabSheet rightContentTabPanel = new TabSheet();
	@SuppressWarnings("rawtypes")
	private final Map<String, com.vaadin.ui.Component> activeComponent = new HashMap<String, com.vaadin.ui.Component>(0);

	public MainView() {
		super();
		addAttachListener(new AttachListener() {
			@Override
			public void attach(AttachEvent event) {
				setLanguage();
			}
		});
	}

	public MainView(com.vaadin.ui.Component compositionRoot) {
		super(compositionRoot);
	
		addAttachListener(new AttachListener() {
			
			@Override
			public void attach(AttachEvent event) {
				setLanguage();
			}
		});
	}
	
	private void setLanguage(){
	
		final com.serpics.base.data.model.Locale locale = (com.serpics.base.data.model.Locale) commerceEngine
				.getCurrentContext().getLocale();
		
		if (locale != null) {
			Locale _locale = new Locale(locale.getLanguage(), locale.getCountry());
			getSession().setLocale(_locale);
		}
		
		
		
		 storeLabel.setCaption(commerceEngine.getCurrentContext().getStoreRealm().getName());
	}

	
	protected void builContent() {
	
      final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		layout.setSizeFull();
	
		final HorizontalLayout toolbarLayout = new HorizontalLayout();
		toolbarLayout.addStyleName("top-toolbar");
		 toolbarLayout.setWidth("100%");
         toolbarLayout.setSpacing(true);
         
         storeLabel.setSizeUndefined();
         toolbarLayout.addComponent(storeLabel);
         toolbarLayout.setExpandRatio(storeLabel, 1);
         toolbarLayout.setComponentAlignment(storeLabel,
                 Alignment.TOP_RIGHT);
		
		final HorizontalLayout menuTitle = new HorizontalLayout();
		menuTitle.addStyleName("valo-menu-title");

		final Label title = new Label("<center><b>Serpics Management <br/> Console</b></center>" , ContentMode.HTML);
		menuTitle.addComponent(title);
		title.setSizeFull();
		title.setStyleName("h3");

		final HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		

		navigatorMenuTree.setWidth("100%");
		
		Panel leftPanel = new Panel();
		leftPanel.addStyleName("valo-menu");
		
		VerticalLayout leftSide = new VerticalLayout();
		
		//leftSide.addStyleName("valo-menu");
		leftSide.addComponent(menuTitle);
		
		final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final MenuItem settingsItem = settings.addItem("", new ThemeResource("../tests-valo/img/profile-pic-300px.jpg") , null);
        settingsItem.addItem("Edit Profile", new MenuBar.Command(){

			@Override
			public void menuSelected(MenuItem selectedItem) {
				JPAContainer<UsersReg> container = ServiceContainerFactory.make(UsersReg.class);
				EntityItem<UsersReg> item = container.getItem(commerceEngine.getCurrentContext().getUserPrincipal().getId());
				EntityFormWindow<UsersReg> _w = new EntityFormWindow<UsersReg>("user.profile");
				_w.addTab(userProfile, "main");
				_w.setEntityItem(item);
				_w.setReadOnly(false);
				UI.getCurrent().addWindow(_w);
				
			}
        	
        });
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out",new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUI().getPage().setLocation("logout?store=" + commerceEngine.getCurrentContext().getStoreRealm().getName());
			}
		});
        
        leftSide.addComponent(settings);
		leftSide.addComponent(navigatorMenuTree);
		
		leftPanel.setContent(leftSide);
		
		content.addComponent(leftPanel);

		navigatorMenuTree.addItemClickListener(new ItemClickListener() {
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
		rightPanel.setExpandRatio(toolbarLayout, 0.050F);
		rightPanel.setExpandRatio(rightContentTabPanel, 1F);
		
		
		content.addComponent(rightPanel);
		content.setExpandRatio(rightPanel, 5);
		content.setExpandRatio(leftPanel, 1);
//
		
		
		layout.addComponent(content);
		layout.setExpandRatio(content, 1);
		
		setCompositionRoot(layout);
	
		setSizeFull();
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
