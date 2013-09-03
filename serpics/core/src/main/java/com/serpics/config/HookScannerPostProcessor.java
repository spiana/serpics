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
import com.serpics.core.hook.GenericHookFactory;
import com.serpics.stereotype.Hook;
import com.serpics.stereotype.HookImplementation;

public class HookScannerPostProcessor implements BeanDefinitionRegistryPostProcessor {

	private static Logger logger = LoggerFactory.getLogger(HookScannerPostProcessor.class);

	private class HookScanner {

		@SuppressWarnings("rawtypes")
		Map<String, Class<?>> hookInterfaceMap = new TreeMap<String, Class<?>>();

		@SuppressWarnings("rawtypes")
		Map<String, Map> hookImplementationMap = new TreeMap<String, Map>();

		abstract class SerpicsClassAnnotationListener implements ClassAnnotationDiscoveryListener {

			@SuppressWarnings("unchecked")
			@Override
			public void discovered(String clazz, String annotation) throws BeansException {
				final String hookClass = Hook.class.getName();
				final String hookImplClass = HookImplementation.class.getName();

				if (clazz.equals(hookClass) || clazz.equals(hookImplClass))
					return;

				if (annotation.equals(hookClass)) {

					try {
						Class<?> c = Class.forName(clazz);
						if (!c.isInterface()) {
							throw new FatalBeanException(
									"Found Hook annotation on Class "
											+ clazz
											+ ". Only interfaces should be annotated as Hook, please use HookImplementation annotation for concrete classes.");
						}
						Hook a = c.getAnnotation(Hook.class);

						hookInterfaceMap.put(a.value(), c);

						logger.info("Discovered HOOK {} on class {}", a.value(), clazz);

					} catch (ClassNotFoundException e) {
						logger.error("Should not happen: ", e);
					}

				} else if (annotation.equals(hookImplClass)) {

					try {
						Class<?> c = Class.forName(clazz);
						if (c.isInterface()) {
							throw new FatalBeanException(
									"Found HookImplementation annotation on Interface "
											+ clazz
											+ ". Only concrete classes should be annotated as HookImplementation, please use Hook annotation for interfaces.");
						}

						HookImplementation a = c.getAnnotation(HookImplementation.class);
						final String hook = a.value();
						final String store = a.store();
						final boolean canOverride = a.override();

						@SuppressWarnings("rawtypes")
						Class hookInterface = hookInterfaceMap.get(hook);
						if (hookInterface == null) {
							throw new FatalBeanException("Class " + clazz + " claims to implement unknown HOOK named "
									+ hook);
						}

						if (!hookInterface.isAssignableFrom(c)) {
							throw new FatalBeanException("Class " + clazz + " claims to implement HOOK " + hook
									+ " but doesn't implement its interface!");
						}

						logger.info("Discovered implementation for HOOK {} and STORE {} : class " + clazz, hook, store);

						Map<String, Class<?>> hookImpls = hookImplementationMap.get(hook);
						if (hookImpls == null) {
							hookImpls = new TreeMap<String, Class<?>>();
							hookImplementationMap.put(hook, hookImpls);
							hookImpls.put(store, Class.forName(clazz));
						} else {
							if (hookImpls.containsKey(store))
								if (canOverride || clazz.equals(hookImpls.get(store).getName()))
									logger.warn("Ovveride implementation for HOOK {} and STORE {} : class " + clazz,
											hook, store);
								else
									throw new FatalBeanException("Duplicate implementation found for HOOK " + hook
											+ ". Found classes: " + clazz + ", " + hookImpls.get(store).getName());

							hookImpls.put(store, Class.forName(clazz));
						}

					} catch (ClassNotFoundException e) {
						logger.error("Should not happen: ", e);
					}

				}
			}

		}

		private void doHookScan() {

			logger.info("start scanning !");
//			Discoverer discoverer = new ClasspathDiscoverer();
			
			Discoverer discoverer = new com.impetus.annovention.ClasspathDiscoverer();
			
			logger.info("discoveder {} URL", discoverer.findResources().length);
			if (logger.isDebugEnabled()) {
				for (URL url : discoverer.findResources()) {
					logger.info("found URL [{}]", url.getPath());
				}
			}

			discoverer.addAnnotationListener(new SerpicsClassAnnotationListener() {

				@Override
				public String[] supportedAnnotations() {
					return new String[] { Hook.class.getName() };
				}

			});
			discoverer.discover(true, false, false, true, false);

//			discoverer = new ClasspathDiscoverer();
			
			discoverer = new com.impetus.annovention.ClasspathDiscoverer();
			
			discoverer.addAnnotationListener(new SerpicsClassAnnotationListener() {

				@Override
				public String[] supportedAnnotations() {
					return new String[] { HookImplementation.class.getName() };
				}

			});
			discoverer.discover(true, false, false, true, false);

		}

		private void perfomScan(BeanDefinitionRegistry registry) {

			doHookScan();

			for (String hook : hookInterfaceMap.keySet()) {
				Class<?> type = hookInterfaceMap.get(hook);

				BeanDefinition definition = new RootBeanDefinition(GenericHookFactory.class);
				definition.getConstructorArgumentValues().addGenericArgumentValue(type);
				definition.getConstructorArgumentValues().addGenericArgumentValue(hookImplementationMap.get(hook));
				definition.setScope("store");
				registry.registerBeanDefinition(hook, definition);
				logger.info("Registered factory {} for Class {}", hook, type.getName());
			}
		}
	}

	public Class<?> getHookImplementation(String storeName, String hookName) {
		// return (Class<?>) storeMap.get(storeName).get(hookName);
		return null;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		HookScanner hookScanner = new HookScanner();
		hookScanner.perfomScan(registry);
	}

	private String getImplementedHookInterface(Class<?> clazz) {
		for (Class<?> c : clazz.getInterfaces()) {
			if (c.getName().endsWith("Hook"))
				return c.getSimpleName();
		}
		return null;

	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

}
