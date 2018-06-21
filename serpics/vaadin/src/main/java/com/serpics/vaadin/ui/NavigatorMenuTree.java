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
/**
 * 
 */
package com.serpics.vaadin.ui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.serpics.core.EngineFactoryUtils;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.vaadin.v7.ui.Tree;


/**
 * @author christian
 *
 */
public class NavigatorMenuTree extends Tree
		implements ApplicationContextAware, InitializingBean, Serializable {

	private static final long serialVersionUID = -3879900712039097486L;

	private static Logger LOG = LoggerFactory.getLogger(NavigatorMenuTree.class);

	private ApplicationContext applicationContext;
	
	private Map<String , String> beans = new HashMap<String , String>();
	private Map<String , String> clazz = new HashMap<String , String>();
	private Map<String , String[]> roles = new HashMap<String , String[]>();
	
	private List<MenuItem> menuItems = new ArrayList<NavigatorMenuTree.MenuItem>();
	
	class MenuItem{
		String name;
		String parent;
		boolean allowChild;
		
		public MenuItem(String name, String parent) {
			super();
			this.name = name;
			this.parent = parent;
			allowChild = false;
		}
		
		public MenuItem(String name, String parent , boolean allowChild) {
			super();
			this.name = name;
			this.parent = parent;
			this.allowChild = allowChild;
		}
	}
	
	public NavigatorMenuTree() {
		super();
		addAttachListener (new AttachListener() {
			
			@Override
			public void attach(AttachEvent event) {
				prepareMenu();
			}
		});
		
	}

	private void prepareMenu(){
	
		// Step 1 add parent menu items
		for (MenuItem _m : menuItems) {
			
			if (!StringUtils.isEmpty(_m.parent))
				continue;
			
			Set<String> roles = new HashSet<String>(Arrays.asList(this.getComponetRoles(_m.name.toString())));
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
			if (roles.contains("*")){
				makeMenuItem(_m);
			}else{
				for (GrantedAuthority grantedAuthority : authorities) {
					if (roles.contains(grantedAuthority.getAuthority()))
							makeMenuItem(_m);
				}
			}
		}
		// Step 2 add child elements
		for (MenuItem _m : menuItems) {
			
			if (StringUtils.isEmpty(_m.parent))
				continue;
			
			Set<String> roles = new HashSet<String>(Arrays.asList(this.getComponetRoles(_m.name.toString())));
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
			if (roles.contains("*")){
				makeMenuItem(_m);
			}else{
				for (GrantedAuthority grantedAuthority : authorities) {
					if (roles.contains(grantedAuthority.getAuthority()))
							makeMenuItem(_m);
				}
			}
		}
	}
	
	
	private void makeMenuItem(MenuItem _m){
		// check if parent else return without add item
		if (!StringUtils.isEmpty(_m.parent))
			if(getItem(_m.parent) == null)
				return;
			
		addItem(_m.name);
		setItemCaption(_m.name,
			I18nUtils.getMessage("smc.navigator."+_m.name,_m.name));
			setParent((Object) _m.name, _m.parent);
			setChildrenAllowed(_m.name, _m.allowChild);
				
	}
	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<Resource> resources  = EngineFactoryUtils.loadResourceByModule(applicationContext, "classpath*:META-INF/", "-navigator.xml");
		for (Resource resource : resources) {
				LOG.info("found smp definition file : {} with URL {}", resource.getFilename(), resource.getURL());
				menuPopulatorFromAmcDefinition(resource.getURL());
		}
	}

	/**
	 * 
	 * @param resourceDefinition
	 */
	public void menuPopulatorFromAmcDefinition(URL resourceDefinition) throws DocumentException, IOException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(resourceDefinition);

		Element root = document.getRootElement();
		for (Iterator<?> i = root.elementIterator("navigator"); i.hasNext();) {
			Element navigator = (Element) i.next();
			final String navigatorName = navigator.attributeValue("name");
			LOG.info("create amc configuration form menu : {}", navigatorName);
			generateSingleNav(navigator, navigatorName);
		}
	}

	/**
	 * 
	 * @param navigator
	 */
	private void generateSingleNav(Element navigator, String navigatorName) {

		for (Iterator<?> i = navigator.elementIterator(); i.hasNext();) {
			Element navItem = (Element) i.next();
			String name = navItem.attributeValue("name");
			
			menuItems.add(new MenuItem(name, navItem.attributeValue("parent") , Boolean.parseBoolean(navItem.attributeValue("allow-child"))));
			
			if (!StringUtils.isEmpty(navItem.attributeValue("bean"))){
				beans.put(navItem.attributeValue("name"), navItem.attributeValue("bean"));
				LOG.info("amc navigator item : {} with bean  {}",navItem.attributeValue("name") ,  navItem.attributeValue("bean"));
			}
			if (!StringUtils.isEmpty(navItem.attributeValue("class"))){
				clazz.put(navItem.attributeValue("name"), navItem.attributeValue("class"));
				LOG.info("amc navigator item : {} for class  {}",navItem.attributeValue("name") ,  navItem.attributeValue("class"));
			}
			
			if (!StringUtils.isEmpty(navItem.attributeValue("roles"))){
				roles.put(navItem.attributeValue("name"), navItem.attributeValue("roles").split(","));
				LOG.info("amc navigator item : {} with roles  {}",navItem.attributeValue("name") ,  navItem.attributeValue("roles"));
			}else{
				roles.put(navItem.attributeValue("name"), new String[] {"*"});
			}

		}
	}

	public String getBeanComponent(String key){
		return beans.get(key);
	}
	public String getClassComponent(String key){
		return clazz.get(key);
	}

	public String[] getComponetRoles(String key){
		return roles.get(key);
	}
}
