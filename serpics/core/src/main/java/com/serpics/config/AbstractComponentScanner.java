/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author spiana
 *
 */
public abstract class AbstractComponentScanner implements ComponentScanner{
	private static Logger logger = LoggerFactory.getLogger(AbstractComponentScanner.class);

	@Override
	public void registerFactory(final BeanDefinitionRegistry registry , ComponentImplementationMap componentImplementationMap ) {

		for (final String service : componentImplementationMap.keySet()) {
			final String scope = componentImplementationMap.get(service).scope;
			if (registry.containsBeanDefinition(service)) {
				BeanDefinition b = registry.getBeanDefinition(service);
				ConstructorArgumentValues values = b
						.getConstructorArgumentValues();
				ValueHolder _v = values.getIndexedArgumentValue(1, Map.class);
				Map<String, Class<?>> _v1 = (Map<String, Class<?>>) _v
						.getValue();
				_v1.putAll(componentImplementationMap.get(service).storeImpl);
				_v.setValue(_v1);
				logger.info(
						"Adding implementation in factory for component {}  and scope : {} !",
						service, scope);
			} else {
				final BeanDefinition definition = new RootBeanDefinition(
						StoreComponentFactory.class);
				definition
						.getConstructorArgumentValues()
						.addGenericArgumentValue(
								componentImplementationMap.get(service).storeImpl);

				definition.setScope(scope);
				registry.registerBeanDefinition(service, definition);
				logger.info(
						"Registered factory for component {}  and scope : {} !",
						service, scope);
			}

		}
	}

}
