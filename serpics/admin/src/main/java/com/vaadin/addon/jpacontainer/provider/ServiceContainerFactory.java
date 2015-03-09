package com.vaadin.addon.jpacontainer.provider;

import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.vaadin.addon.jpacontainer.JPAContainer;

public class ServiceContainerFactory {
	
    @SuppressWarnings({  "unchecked", "rawtypes" })
    public static  <T> JPAContainer<T> make(final Class<T> entityClass) {
        final JPAContainer<T> cont = new JPAContainer<T>(entityClass);
        final CachingLocalEntityServiceProvider<T> provider = new CachingLocalEntityServiceProvider<T>(entityClass);
        provider.setCacheEnabled(true);
        cont.setEntityProvider(provider);
        provider.setRepository(RepositoryInitializer.getInstance().getRepositoryForEntity(entityClass));
       
        return cont;
    }

}
