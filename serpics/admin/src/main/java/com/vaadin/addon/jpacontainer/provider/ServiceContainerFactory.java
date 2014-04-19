package com.vaadin.addon.jpacontainer.provider;

import com.serpics.core.service.EntityService;
import com.vaadin.addon.jpacontainer.JPAContainer;

public class ServiceContainerFactory {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> JPAContainer<T> make(final Class<T> entityClass, final EntityService service) {

        final JPAContainer<T> cont = new JPAContainer<T>(entityClass);
        final CachingLocalEntityServiceProvider<T> provider = new CachingLocalEntityServiceProvider<T>(entityClass);
        provider.setCacheEnabled(true);
        cont.setEntityProvider(provider);
        provider.setService(service);
        return cont;
    }

}
