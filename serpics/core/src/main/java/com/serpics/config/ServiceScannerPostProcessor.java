package com.serpics.config;

import java.net.URL;
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

import com.impetus.annovention.Discoverer;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import com.serpics.stereotype.Service;

public class ServiceScannerPostProcessor implements
		BeanDefinitionRegistryPostProcessor {

	private static Logger logger = LoggerFactory
			.getLogger(ServiceScannerPostProcessor.class);

	private class ServiceScanner {

		Map<String, Map<String, Class<?>>> serviceImplementationMap = new TreeMap<String, Map<String, Class<?>>>();
		Map<String, Class<?>> serviceMap = new TreeMap<String, Class<?>>();

		abstract private class ServiceClassAnnotationListener implements
				ClassAnnotationDiscoveryListener {

			@Override
			public void discovered(String clazz, String annotation)
					throws BeansException {
				final String serviceClass = Service.class.getName();

				if (clazz.equals(serviceClass))
					return;

				if (annotation.equals(serviceClass)) {

					try {
						Class<?> c = Class.forName(clazz);
						if (c.isInterface()) {
							throw new FatalBeanException(
									"Found ServiceImplementation annotation on Interface "
											+ clazz
											+ ". Only concrete classes should be annotated as ServiceImplementation.");
						}

						Service a = c.getAnnotation(Service.class);
						final String service = a.value();
						final String[] stores = a.store();

						logger.info(
								"Discovered implementation for SERVICE {} and STORE {} : class "
										+ clazz, service, stores);

						if (!serviceMap.containsKey(service)) {
							if (logger.isDebugEnabled())
								logger.debug(
										"Adding discovered Service {} to serviceMap",
										service);
								
							serviceMap.put(service, getImplementedInterface(Class.forName(clazz) ) );
						}

						Map<String, Class<?>> serviceImpls = serviceImplementationMap
								.get(service);

						if (serviceImpls == null) {
							serviceImpls = new TreeMap<String, Class<?>>();
							for (String store : stores) {
								serviceImpls.put(store, Class.forName(clazz));
							}
							serviceImplementationMap.put(service, serviceImpls);
						} else {
							for (String store : stores) {
								if (serviceImpls.containsKey(store))
									if (clazz.equals(serviceImpls.get(store)
											.getName()))
										logger.warn(
												"Ovveride implementation for SERVICE {} and STORE {} : class "
														+ clazz, service, store);
									else
										throw new FatalBeanException(
												"Duplicate implementation found for Service "
														+ service
														+ ". Found classes: "
														+ clazz
														+ ", "
														+ serviceImpls.get(
																store)
																.getName());

								serviceImpls.put(store, Class.forName(clazz));
							}

						}

					} catch (ClassNotFoundException e) {
						logger.error("Should not happen: ", e);
					}

				}
			}

		}

		private void doServiceScan() {

			logger.info("start scanning !");
			// Discoverer discoverer = new ClasspathDiscoverer();

			Discoverer discoverer = new com.impetus.annovention.ClasspathDiscoverer();

			logger.info("discoveder {} URL", discoverer.findResources().length);
			if (logger.isDebugEnabled()) {
				for (URL url : discoverer.findResources()) {
					logger.info("found URL [{}]", url.getPath());
				}
			}

			discoverer
					.addAnnotationListener(new ServiceClassAnnotationListener() {

						@Override
						public String[] supportedAnnotations() {
							return new String[] { Service.class.getName() };
						}

					});
			discoverer.discover(true, false, false, true, false);

	
		}

		private void perfomScan(BeanDefinitionRegistry registry) {

			doServiceScan();

			for (String service : serviceImplementationMap.keySet()) {
				Class<?> type = serviceMap.get(service);

				BeanDefinition definition = new RootBeanDefinition(
					GenericComponentFactory.class);
				definition.getConstructorArgumentValues()
						.addGenericArgumentValue(type);
				definition.getConstructorArgumentValues()
						.addGenericArgumentValue(
								serviceImplementationMap.get(service));
				definition.setScope("store");
				registry.registerBeanDefinition(service, definition);
				logger.info("Registered factory {} for Class {}", service	, type.getName());
			}
		}
	}

	public Class<?> getHookImplementation(String storeName, String hookName) {
		// return (Class<?>) storeMap.get(storeName).get(hookName);
		return null;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		ServiceScanner hookScanner = new ServiceScanner();
		hookScanner.perfomScan(registry);
	}

	private Class<?> getImplementedInterface(Class<?> clazz) {
		for (Class<?> c : clazz.getInterfaces()) {
			if (clazz.getSimpleName().contains(c.getSimpleName()))
				return c;
		}
		return null;

	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

}
