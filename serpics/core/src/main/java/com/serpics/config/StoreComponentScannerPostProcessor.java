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
package com.serpics.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class StoreComponentScannerPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private static Logger logger = LoggerFactory.getLogger(StoreComponentScannerPostProcessor.class);
    
    private String basePackage=null;

  //  @Required
    public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	ClasspathComponentScanner componentScanner = new ClasspathComponentScanner();

	
    private ComponentImplementationMap doServiceScan() {
        logger.info("start Service scanning scope store !");
        ComponentImplementationMap _m = componentScanner.loadCustomComponents();
        logger.info("stop scanning !");
        return _m;
    }

    private void perfomScan(final BeanDefinitionRegistry registry) {
    	componentScanner.setBasePackage(basePackage);
        ComponentImplementationMap m = doServiceScan();
        componentScanner.registerFactory(registry , m);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
        perfomScan(registry);
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


}
