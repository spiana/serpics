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
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.Assert;

import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import com.serpics.stereotype.StoreComponent;
import com.serpics.stereotype.StoreFacade;
import com.serpics.stereotype.StoreHook;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.VaadinComponent;

class ComponentScanner {
	private static Logger logger = LoggerFactory
			.getLogger(ComponentScanner.class);

	
	class ComponentBean {
		Map<String , Class<?>> storeImpl;
		String scope;
		public ComponentBean(Map<String,Class<?>> storeImpl, String scope) {
			this.storeImpl = storeImpl;
			this.scope = scope;
		}
		
	}
	Map<String, Map<String, Class<?>>> componentImplementationMap =  new TreeMap<String, Map<String, Class<?>>>();

	abstract  class ComponentClassAnnotationListener implements
			ClassAnnotationDiscoveryListener {

		@Override
		public void discovered(String clazz, String annotation)
				throws BeansException {

			try {
				Class<?> implementedClass = Class.forName(clazz);

				if (implementedClass.isInterface()) {
					return;
				}
				String component = null;
				String[] stores = {};
				String scope = "store";

				if (annotation.equals(StoreHook.class.getName())) {
					StoreHook a = implementedClass
							.getAnnotation(StoreHook.class);
					component = a.value();
					stores = new String[] { a.store() };
					logger.info("Discovered implementation for hook ["
							+ component + "] and store {} : class [" + clazz
							+ "] interfaces {}", stores,
							getImplementedInterface(implementedClass));

				} else if (annotation.equals(StoreService.class.getName())) {
					StoreService a = implementedClass
							.getAnnotation(StoreService.class);
					component = a.value();
					stores = a.stores();
					logger.info("Discovered implementation for service ["
							+ component + "] and store {} : class [" + clazz
							+ "] interfaces {}", stores,
							getImplementedInterface(implementedClass));
				} else if (annotation.equals(StoreHook.class.getName())) {
					StoreHook a = implementedClass
							.getAnnotation(StoreHook.class);
					component = a.value();
					stores = new String[] { a.store() };
					logger.info("Discovered implementation for hook ["
							+ component + "] and store {} : class [" + clazz
							+ "] interfaces {}", stores,
							getImplementedInterface(implementedClass));

				} else if (annotation.equals(StoreFacade.class.getName())) {
					StoreFacade a = implementedClass
							.getAnnotation(StoreFacade.class);
					component = a.value();
					stores = a.stores();
					logger.info("Discovered implementation for facade ["
							+ component + "] and store {} : class [" + clazz
							+ "] interfaces {}", stores,
							getImplementedInterface(implementedClass));

				} else if (annotation.equals(StoreComponent.class.getName())) {
					StoreComponent a = implementedClass
							.getAnnotation(StoreComponent.class);
					component = a.value();
					stores = a.stores();
					logger.info("Discovered implementation for component ["
							+ component + "] and store {} : class [" + clazz
							+ "] interfaces {}", stores,
							getImplementedInterface(implementedClass));
				}else if (annotation.equals(VaadinComponent.class.getName())) {
					VaadinComponent a = implementedClass
							.getAnnotation(VaadinComponent.class);
					component = a.value();
					stores = a.stores();
					logger.info(
							"Discovered implementation for VaadinComponent ["
									+ component + "] and store {} : class ["
									+ clazz + "] interfaces {}", stores,
							getImplementedInterface(implementedClass));

				}

				Assert.notNull(component);

				Map<String, Class<?>> serviceImpls = componentImplementationMap
						.get(component);

				if (serviceImpls == null) {
					serviceImpls = new TreeMap<String, Class<?>>();

					for (String store : stores) {
						serviceImpls.put(store,implementedClass);
					}
					componentImplementationMap.put(component, serviceImpls);
				} else {

					for (String store : stores) {
						if (serviceImpls.containsKey(store))
							logger.warn(
									"Ovveride implementation for Component [{}] and store [{}] : class ["
											+ clazz + "] was ["
											+ serviceImpls.get(store).getName()
											+ "]", component, store);
						serviceImpls.put(store, implementedClass);
					}

				}

			} catch (ClassNotFoundException e) {
				throw new FatalBeanException("Should not happen!", e);
			}

		}
	}
	
	
	 void doScan(final String[] annotations , ClasspathDiscoverer discoverer) {
		logger.info("perform component scanning !");
		// Discoverer discoverer = new ClasspathDiscoverer();
		//ClasspathDiscoverer discoverer = new com.impetus.annovention.ClasspathDiscoverer();

		logger.info("discoveder {} URL", discoverer.findResources().length);
		if (logger.isDebugEnabled()) {
			for (URL url : discoverer.findResources()) {
				logger.debug("found URL [{}]", url.getPath());
			}
		}
		discoverer.addAnnotationListener(new ComponentClassAnnotationListener() {
			
			@Override
			public String[] supportedAnnotations() {
				return annotations;
			}
		});
		
		discoverer.discover(true, false, false, true, false);

	}

	 void registerFactory(BeanDefinitionRegistry registry , String store) {

		for (String service : componentImplementationMap.keySet()) {
			BeanDefinition definition = new RootBeanDefinition(
					StoreComponentFactory.class);
			definition.getConstructorArgumentValues()
					.addGenericArgumentValue(
							componentImplementationMap.get(service));
			definition.setScope(store);
			// definition.setBeanClassName);
			registry.registerBeanDefinition(service, definition);
			logger.info("Registered factory for component {} !", service);
		}
	}
	
	private List<String> getImplementedInterface(Class<?> clazz) {
		List<String> clist = new ArrayList<String>(0);

		for (Class<?> c : clazz.getInterfaces()) {
			clist.add(c.getName());
		}
		return clist;

	}
	
}
