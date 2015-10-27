/**
 * 
 */
package com.serpics.vaadin.ui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Iterator;

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

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Tree;

/**
 * @author christian
 *
 */
public class NavGeneratoFactory extends AbstractSelect
		implements ApplicationContextAware, InitializingBean, Serializable {

	private static final long serialVersionUID = -3879900712039097486L;

	private static Logger LOG = LoggerFactory.getLogger(NavGeneratoFactory.class);

	private ApplicationContext applicationContext;

	private Tree navigator;

	public NavGeneratoFactory() {
		super();
	}

	/**
	 * @return the navigator
	 */
	public Tree getNavigator() {
		return navigator;
	}

	/**
	 * @param navigator
	 *            the navigator to set
	 */
	public void setNavigator(final Tree navigator) {
		this.navigator = navigator;
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

		Resource[] resources = this.applicationContext.getResources("classpath*:META-INF/*-amc.xml");

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

		final Tree currentTree = new Tree();

		for (Iterator<?> i = navigator.elementIterator(); i.hasNext();) {
			Element navItem = (Element) i.next();
			if (Boolean.parseBoolean(navItem.attributeValue("allow-child"))) {
				currentTree.addItem(navItem.attributeValue("name"));
			} else {
				currentTree.addItem(navItem.attributeValue("name"));
				currentTree.setParent(navItem.attributeValue("name"), navItem.attributeValue("parent"));
				currentTree.setChildrenAllowed(navItem.attributeValue("name"),
						Boolean.parseBoolean(navItem.attributeValue("allow-child")));
			}

		}
		LOG.info("created navigator tree from admin generator: {} with name {}", NavGeneratoFactory.class.getName(),
				navigatorName);
		setNavigator(currentTree);
	}

	/**
	 * @return the navigator
	 * @throws Exception
	 */
	public Tree generateNavigator() {
		try {
			afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getNavigator();
	}

}
