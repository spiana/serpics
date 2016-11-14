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

import java.net.URL;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.Filter;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import com.serpics.stereotype.StoreComponent;
import com.serpics.stereotype.StoreFacade;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.stereotype.VaadinComponent;

class ClasspathComponentScanner extends AbstractComponentScanner implements ComponentScanner {
	private static Logger logger = LoggerFactory
			.getLogger(ClasspathComponentScanner.class);

	private String basePackage;

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	class ClassPathFilterImpl implements Filter {
		private final String[] IGNORED_PACKAGES = { "java", "javax", "sun",
				"com.sun", "apple", "com.apple", "scalaj", "scala.tools.jline",
				"org.scala_tools.time", "javassist", "com.impetus.annovention",
				" org.springframework" };

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.impetus.annovention.Filter#accepts(java.lang.String)
		 */
		@Override
		public boolean accepts(String paramString) {
			if (paramString.endsWith(".class")) {
				if (paramString.startsWith("/")) {
					paramString = paramString.substring(1);
				}
				if (!(ignoreScan(paramString.replace('/', '.')))) {
					return true;
				}
			}
			return false;
		}

		private boolean ignoreScan(String paramString) {
			for (String str : IGNORED_PACKAGES) {
				if (paramString.startsWith(str + ".")) {
					return true;
				}
			}
			return false;
		}

	}

	ComponentImplementationMap componentImplementationMap = new ComponentImplementationMap();

	abstract class ComponentClassAnnotationListener implements
			ClassAnnotationDiscoveryListener {

		@Override
		public void discovered(final String clazz, final String annotation)
				throws BeansException {

			try {
				final Class<?> implementedClass = Class.forName(clazz);

				if (implementedClass.isInterface()) {
					return;
				}
				String component = null;
				String[] stores = {};
				String scope = null;

				if (annotation.equals(StoreStrategy.class.getName())) {
					final StoreStrategy a = implementedClass
							.getAnnotation(StoreStrategy.class);
					component = a.value();
					stores = new String[] { a.store() };
					scope = a.scope();
				} else if (annotation.equals(StoreService.class.getName())) {
					final StoreService a = implementedClass
							.getAnnotation(StoreService.class);
					component = a.value();
					stores = a.stores();
					scope = a.scope();
				} else if (annotation.equals(StoreFacade.class.getName())) {
					final StoreFacade a = implementedClass
							.getAnnotation(StoreFacade.class);
					component = a.value();
					stores = a.stores();
					scope = a.scope();
				} else if (annotation.equals(StoreComponent.class.getName())) {
					final StoreComponent a = implementedClass
							.getAnnotation(StoreComponent.class);
					component = a.value();
					stores = a.stores();
					scope = a.scope();
				} else if (annotation.equals(VaadinComponent.class.getName())) {
					final VaadinComponent a = implementedClass
							.getAnnotation(VaadinComponent.class);
					component = a.value();
					stores = a.stores();
					scope = a.scope();
				}

				logger.info("Discovered implementation for component ["
						+ component + "] and store {}  scope [" + scope
						+ "]  class [" + clazz + "]  type [{}]", stores,
						annotation);

				Assert.notNull(component);

				ComponentBean serviceImpls = componentImplementationMap
						.get(component);

				if (serviceImpls == null) {
					serviceImpls = new ComponentBean(
							new TreeMap<String, Class<?>>(), scope);

					for (final String store : stores) {
						serviceImpls.storeImpl.put(store, implementedClass);
					}
					componentImplementationMap.put(component, serviceImpls);
				} else {

					for (final String store : stores) {
						if (serviceImpls.storeImpl.containsKey(store)) {
							logger.warn(
									"Ovveride implementation for Component [{}] and store [{}] : class ["
											+ clazz
											+ "] was ["
											+ serviceImpls.storeImpl.get(store)
													.getName() + "]",
									component, store);
						}
						serviceImpls.storeImpl.put(store, implementedClass);
					}

				}

			} catch (final ClassNotFoundException e) {
				throw new FatalBeanException("Should not happen!", e);
			}

		}
	}

	void doScan(final String[] annotations, final ClasspathDiscoverer discoverer) {
		logger.info("perform component scanning !");
		// Discoverer discoverer = new ClasspathDiscoverer();
		// ClasspathDiscoverer discoverer = new
		// com.impetus.annovention.ClasspathDiscoverer();

		logger.debug(Thread.currentThread().getContextClassLoader().getClass()
				.getName());
		logger.info("discoveder {} URL", discoverer.findResources().length);
		if (logger.isDebugEnabled()) {
			for (final URL url : discoverer.findResources()) {
				logger.debug("found URL [{}]", url.getPath());
			}
		}
		discoverer
				.addAnnotationListener(new ComponentClassAnnotationListener() {

					@Override
					public String[] supportedAnnotations() {
						return annotations;
					}
				});

		discoverer.discover(true, false, false, true, false);

	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serpics.config.ComponetScanner#loadCustomComponents()
	 */
	@Override
	public ComponentImplementationMap loadCustomComponents() {
		final String[] annotations = { StoreService.class.getName(),
				StoreStrategy.class.getName(), StoreFacade.class.getName(),
				StoreComponent.class.getName(), VaadinComponent.class.getName() };
		ClasspathDiscoverer discoverer = new ClasspathDiscoverer();
		if (basePackage != null)
			discoverer.setFilter(new BasePackageFilter(this.basePackage));
		else
			discoverer.setFilter(new ClassPathFilterImpl());
		
		doScan(annotations, discoverer);

		return componentImplementationMap;
	}

}
