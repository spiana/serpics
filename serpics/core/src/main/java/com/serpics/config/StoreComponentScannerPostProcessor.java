package com.serpics.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.Assert;

import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.Discoverer;
import com.impetus.annovention.Filter;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import com.serpics.stereotype.StoreComponent;
import com.serpics.stereotype.StoreFacade;
import com.serpics.stereotype.StoreHook;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.VaadinComponent;

public class StoreComponentScannerPostProcessor implements
		BeanDefinitionRegistryPostProcessor {

	private static Logger logger = LoggerFactory
			.getLogger(StoreComponentScannerPostProcessor.class);

	ComponentScanner componentScanner = new ComponentScanner();
	
	private void doServiceScan() {

		logger.info("start Service scanning scope store !");
		// Discoverer discoverer = new ClasspathDiscoverer();

		String[] annotations = {
		StoreService.class.getName(),StoreHook.class.getName(),StoreFacade.class.getName() , StoreComponent.class
		.getName() };
		componentScanner.doScan(annotations , new ClasspathDiscoverer());
		
		logger.info("stop scanning !");
	}
	
	
	
	private void perfomScan(BeanDefinitionRegistry registry) {
		doServiceScan();
		componentScanner.registerFactory(registry, "store");
	}
	
	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		perfomScan(registry);
	}
		
	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

}
