/**
 * 
 */
package com.serpics.vaadin.ui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import org.springframework.util.StringUtils;

import com.vaadin.ui.Tree;

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
	
	public NavigatorMenuTree() {
		super();
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

		Resource[] resources = this.applicationContext.getResources("classpath:META-INF/*-amc.xml");

		for (Resource resource : resources) {
	//		if (UrlResource.class.isAssignableFrom(resource.getClass())){
				LOG.info("found smp definition file : {} with URL {}", resource.getFilename(), resource.getURL());
				menuPopulatorFromAmcDefinition(resource.getURL());
		//	}
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
			if (Boolean.parseBoolean(navItem.attributeValue("allow-child"))) {
				this.addItem(name);
				
			} else {
				this.addItem(name);
				this.setParent(navItem.attributeValue("name"), navItem.attributeValue("parent"));
				this.setChildrenAllowed(navItem.attributeValue("name"),
						Boolean.parseBoolean(navItem.attributeValue("allow-child")));
			}
			
			if (!StringUtils.isEmpty(navItem.attributeValue("bean"))){
				beans.put(navItem.attributeValue("name"), navItem.attributeValue("bean"));
				LOG.info("amc navigator item : {} with bean  {}",navItem.attributeValue("name") ,  navItem.attributeValue("bean"));
			}
			if (!StringUtils.isEmpty(navItem.attributeValue("class"))){
				clazz.put(navItem.attributeValue("name"), navItem.attributeValue("class"));
				LOG.info("amc navigator item : {} with bean  {}",navItem.attributeValue("name") ,  navItem.attributeValue("class"));
			}
			

		}
	}

	public String getBeanComponent(String key){
		return beans.get(key);
	}
	public String getClassComponent(String key){
		return clazz.get(key);
	}

}
