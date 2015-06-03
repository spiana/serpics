package com.serpics.config;

import java.net.URL;
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
import com.serpics.stereotype.StoreStrategy;
import com.serpics.stereotype.StoreService;
import com.serpics.stereotype.VaadinComponent;

class ComponentScanner {
    private static Logger logger = LoggerFactory.getLogger(ComponentScanner.class);

    class ComponentBean {
        Map<String, Class<?>> storeImpl;
        String scope;

        public ComponentBean(final Map<String, Class<?>> storeImpl, final String scope) {
            this.storeImpl = storeImpl;
            this.scope = scope;
        }

    }

    Map<String, ComponentBean> componentImplementationMap = new TreeMap<String, ComponentBean>();

    abstract class ComponentClassAnnotationListener implements ClassAnnotationDiscoveryListener {

        @Override
        public void discovered(final String clazz, final String annotation) throws BeansException {

            try {
                final Class<?> implementedClass = Class.forName(clazz);

                if (implementedClass.isInterface()) {
                    return;
                }
                String component = null;
                String[] stores = {};
                String scope = null;

                if (annotation.equals(StoreStrategy.class.getName())) {
                    final StoreStrategy a = implementedClass.getAnnotation(StoreStrategy.class);
                    component = a.value();
                    stores = new String[] { a.store() };
                    scope = a.scope();
                } else if (annotation.equals(StoreService.class.getName())) {
                    final StoreService a = implementedClass.getAnnotation(StoreService.class);
                    component = a.value();
                    stores = a.stores();
                    scope = a.scope();
                } else if (annotation.equals(StoreFacade.class.getName())) {
                    final StoreFacade a = implementedClass.getAnnotation(StoreFacade.class);
                    component = a.value();
                    stores = a.stores();
                    scope = a.scope();
                } else if (annotation.equals(StoreComponent.class.getName())) {
                    final StoreComponent a = implementedClass.getAnnotation(StoreComponent.class);
                    component = a.value();
                    stores = a.stores();
                    scope = a.scope();
                } else if (annotation.equals(VaadinComponent.class.getName())) {
                    final VaadinComponent a = implementedClass.getAnnotation(VaadinComponent.class);
                    component = a.value();
                    stores = a.stores();
                    scope = a.scope();
                }

                logger.info("Discovered implementation for component [" + component + "] and store {}  scope [" + scope
                        + "]  class [" + clazz + "]  type [{}]", stores, annotation);

                Assert.notNull(component);

                ComponentBean serviceImpls = componentImplementationMap.get(component);

                if (serviceImpls == null) {
                    serviceImpls = new ComponentBean(new TreeMap<String, Class<?>>(), scope);

                    for (final String store : stores) {
                        serviceImpls.storeImpl.put(store, implementedClass);
                    }
                    componentImplementationMap.put(component, serviceImpls);
                } else {

                    for (final String store : stores) {
                        if (serviceImpls.storeImpl.containsKey(store)) {
                            logger.warn("Ovveride implementation for Component [{}] and store [{}] : class [" + clazz
                                    + "] was [" + serviceImpls.storeImpl.get(store).getName() + "]", component, store);
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
        // ClasspathDiscoverer discoverer = new com.impetus.annovention.ClasspathDiscoverer();

        logger.debug(Thread.currentThread().getContextClassLoader().getClass().getName());
        logger.info("discoveder {} URL", discoverer.findResources().length);
        if (logger.isDebugEnabled()) {
            for (final URL url : discoverer.findResources()) {
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

    void registerFactory(final BeanDefinitionRegistry registry) {

        for (final String service : componentImplementationMap.keySet()) {
            final BeanDefinition definition = new RootBeanDefinition(StoreComponentFactory.class);
            definition.getConstructorArgumentValues().addGenericArgumentValue(
                    componentImplementationMap.get(service).storeImpl);
            final String scope = componentImplementationMap.get(service).scope;
            definition.setScope(scope);

            registry.registerBeanDefinition(service, definition);
            logger.info("Registered factory for component {}  and scope : {} !", service, scope);
        }
    }

}
