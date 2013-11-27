package com.serpics.config;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javassist.expr.Instanceof;

import org.hamcrest.core.IsAnything;
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
import org.springframework.util.StringUtils;
import org.springframework.util.comparator.InstanceComparator;

import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.Discoverer;
import com.impetus.annovention.Filter;
import com.impetus.annovention.FilterImpl;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreFacade;
import com.serpics.stereotype.StoreHook;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.VaadinComponent;

public class ComponentScannerPostProcessor implements
		BeanDefinitionRegistryPostProcessor {

	private static Logger logger = LoggerFactory
			.getLogger(ComponentScannerPostProcessor.class);

	private class ServiceScanner {

		Map<String, Map<String, Class<?>>> componentImplementationMap = null;

		abstract private class ServiceClassAnnotationListener implements
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

					if (annotation.equals(StoreService.class.getName())) {
						StoreService a = implementedClass
								.getAnnotation(StoreService.class);
						component = a.value();
						stores = a.stores();
						logger.info("Discovered implementation for service ["
								+ component + "] and store {} : class ["
								+ clazz + "] interfaces {}", stores,
								getImplementedInterface(implementedClass));
					} else if (annotation.equals(StoreHook.class.getName())) {
						StoreHook a = implementedClass
								.getAnnotation(StoreHook.class);
						component = a.value();
						stores = new String[] { a.store() };
						logger.info("Discovered implementation for hook ["
								+ component + "] and store {} : class ["
								+ clazz + "] interfaces {}", stores,
								getImplementedInterface(implementedClass));

					} else if (annotation.equals(StoreFacade.class.getName())) {
						StoreFacade a = implementedClass
								.getAnnotation(StoreFacade.class);
						component = a.value();
						stores = a.stores();
						logger.info("Discovered implementation for facade ["
								+ component + "] and store {} : class ["
								+ clazz + "] interfaces {}", stores,
								getImplementedInterface(implementedClass));
					} else if (annotation.equals(VaadinComponent.class
							.getName())) {
						VaadinComponent a = implementedClass
								.getAnnotation(VaadinComponent.class);
						component = a.value();
						stores = a.stores();
						logger.info(
								"Discovered implementation for VaadinComponent ["
										+ component
										+ "] and store {} : class [" + clazz
										+ "] interfaces {}", stores,
								getImplementedInterface(implementedClass));

					}
					Assert.notNull(component);

					Map<String, Class<?>> serviceImpls = componentImplementationMap
							.get(component);

					if (serviceImpls == null) {
						serviceImpls = new TreeMap<String, Class<?>>();

						for (String store : stores) {
							serviceImpls.put(store, implementedClass);
						}
						componentImplementationMap.put(component, serviceImpls);
					} else {

						for (String store : stores) {
							if (serviceImpls.containsKey(store))
								logger.warn(
										"Ovveride implementation for Component [{}] and store [{}] : class ["
												+ clazz
												+ "] was ["
												+ serviceImpls.get(store)
														.getName() + "]",
										component, store);
							serviceImpls.put(store, implementedClass);
						}

					}

				} catch (ClassNotFoundException e) {
					throw new FatalBeanException("Should not happen!", e);
				}

			}

		}

		private void doServiceScan() {

			logger.info("start Service scanning !");
			// Discoverer discoverer = new ClasspathDiscoverer();

			ClasspathDiscoverer discoverer = new com.impetus.annovention.ClasspathDiscoverer();

			logger.info("discoveder {} URL", discoverer.findResources().length);
			if (logger.isDebugEnabled()) {
				for (URL url : discoverer.findResources()) {
					logger.debug("found URL [{}]", url.getPath());
				}
			}

			discoverer
					.addAnnotationListener(new ServiceClassAnnotationListener() {

						@Override
						public String[] supportedAnnotations() {
							return new String[] { StoreService.class.getName() };
						}

					});

			discoverer
					.addAnnotationListener(new ServiceClassAnnotationListener() {

						@Override
						public String[] supportedAnnotations() {
							return new String[] { StoreHook.class.getName() };
						}

					});

			discoverer
					.addAnnotationListener(new ServiceClassAnnotationListener() {

						@Override
						public String[] supportedAnnotations() {
							return new String[] { StoreFacade.class.getName() };
						}

					});
			discoverer.discover(true, false, false, true, false);

			discoverer
					.addAnnotationListener(new ServiceClassAnnotationListener() {

						@Override
						public String[] supportedAnnotations() {
							return new String[] { VaadinComponent.class
									.getName() };
						}

					});
			discoverer.discover(true, false, false, true, false);

			logger.info("stop scanning !");

		}

		private void doVaadinComponentScan() {
			logger.info("start Service scanning !");
			ClasspathDiscoverer discoverer = new com.impetus.annovention.ClasspathDiscoverer();

			discoverer
					.addAnnotationListener(new ServiceClassAnnotationListener() {

						@Override
						public String[] supportedAnnotations() {
							return new String[] { VaadinComponent.class
									.getName() };
						}

					});
			discoverer.discover(true, false, false, true, false);
		}

		private void perfomScan(BeanDefinitionRegistry registry) {
			// reset Service Map
			componentImplementationMap = new TreeMap<String, Map<String, Class<?>>>();
			doServiceScan();
			registerFactory(registry, "store");
			// reset Service Map
			componentImplementationMap = new TreeMap<String, Map<String, Class<?>>>();
			// Scan for Vaadin component
			doVaadinComponentScan();
			registerFactory(registry, "prototype");
		}

		private void registerFactory(BeanDefinitionRegistry registry,
				String scope) {

			for (String service : componentImplementationMap.keySet()) {
				BeanDefinition definition = new RootBeanDefinition(
						StoreComponentFactory.class);
				definition.getConstructorArgumentValues()
						.addGenericArgumentValue(
								componentImplementationMap.get(service));
				definition.setScope(scope);
				// definition.setBeanClassName);
				registry.registerBeanDefinition(service, definition);
				logger.info("Registered factory for component {} !", service);
			}
		}
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		ServiceScanner hookScanner = new ServiceScanner();
		hookScanner.perfomScan(registry);
	}

	private List<String> getImplementedInterface(Class<?> clazz) {
		List<String> clist = new ArrayList<String>(0);

		for (Class<?> c : clazz.getInterfaces()) {
			clist.add(c.getName());
		}
		return clist;

	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

}
