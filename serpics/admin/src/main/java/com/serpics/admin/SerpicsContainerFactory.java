package com.serpics.admin;

import com.serpics.core.service.EntityService;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.provider.SerpicsCachingLocalEntityProvider;

public class SerpicsContainerFactory {

    public static <T> SerpicsPersistentContainer<T> make(final Class<T> entityClass, final EntityService service) {

        final SerpicsPersistentContainer<T> cont = new SerpicsPersistentContainer<T>(entityClass);
        final SerpicsCachingLocalEntityProvider<T> provider = new SerpicsCachingLocalEntityProvider<T>(entityClass);
        provider.setCacheEnabled(true);
        cont.setEntityProvider(provider);
        provider.setService(service);
        return cont;
    }

}
