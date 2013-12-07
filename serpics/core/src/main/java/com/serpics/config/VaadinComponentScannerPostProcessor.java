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
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import com.serpics.stereotype.StoreComponent;
import com.serpics.stereotype.StoreFacade;
import com.serpics.stereotype.StoreHook;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.VaadinComponent;

public class VaadinComponentScannerPostProcessor implements
		BeanDefinitionRegistryPostProcessor {

	private static Logger logger = LoggerFactory
			.getLogger(VaadinComponentScannerPostProcessor.class);

	ComponentScanner componentScanner = new ComponentScanner();
	
	private void doScan() {
		logger.info("start vaadin component scanning scope store !");
		String[] annotations = {VaadinComponent.class.getName()};
		componentScanner.doScan(annotations , new ClasspathDiscoverer());
		logger.info("stop scanning !");
	}
	
	
	
	private void perfomScan(BeanDefinitionRegistry registry) {
		doScan();
		componentScanner.registerFactory(registry, "prototype");
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
