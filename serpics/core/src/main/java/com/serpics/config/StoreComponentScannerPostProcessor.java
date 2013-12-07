package com.serpics.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import com.impetus.annovention.ClasspathDiscoverer;
import com.serpics.stereotype.StoreComponent;
import com.serpics.stereotype.StoreFacade;
import com.serpics.stereotype.StoreHook;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.VaadinComponent;

public class StoreComponentScannerPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private static Logger logger = LoggerFactory.getLogger(StoreComponentScannerPostProcessor.class);

    ComponentScanner componentScanner = new ComponentScanner();

    private void doServiceScan() {

        logger.info("start Service scanning scope store !");
        // Discoverer discoverer = new ClasspathDiscoverer();

        final String[] annotations = { StoreService.class.getName(), StoreHook.class.getName(),
                StoreFacade.class.getName(), StoreComponent.class.getName(), VaadinComponent.class.getName() };
        componentScanner.doScan(annotations, new ClasspathDiscoverer());

        logger.info("stop scanning !");
    }

    private void perfomScan(final BeanDefinitionRegistry registry) {
        doServiceScan();
        componentScanner.registerFactory(registry);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
        perfomScan(registry);
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


}
