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
package com.vaadin.addon.jpacontainer.provider;

import java.util.List;

import com.vaadin.addon.jpacontainer.CachingEntityProvider;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.data.Container.Filter;

public class  CachingLocalEntityServiceProvider<T> extends EntityRepositoryProvider<T>
implements CachingEntityProvider<T> {

    // TODO Check how well caching works with concurrent users
    // Maybe some of the collections/maps should be replaced with
    // concurrent implementations? What about synchronization?

    private static final long serialVersionUID = 302600441430870363L;
    private final CachingSupport<T> cachingSupport;

    /**
     * Creates a new <code>CachingLocalEntityProvider</code>. The entity manager
     * must be set using
     * {@link #setEntityManager(javax.persistence.EntityManager) }.
     * 
     * @param entityClass
     *            the entity class (must not be null).
     */
    public CachingLocalEntityServiceProvider(final Class<T> entityClass) {
        super(entityClass);
        cachingSupport = new CachingSupport<T>(this);
    }

    @Override
    public void updateEntityProperty(final Object entityId, final String propertyName,
            final Object propertyValue) throws IllegalArgumentException {
        super.updateEntityProperty(entityId, propertyName, propertyValue);
        cachingSupport.invalidate(entityId, true);
    }


    @Override
    public T addEntity(final T entity) {
        super.addEntity(entity);
        cachingSupport.entityAdded(entity);
        return entity;
    }

    public T updateEntity(T entity) {
        super.updateEntity(entity);
        Object  id  = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        cachingSupport.invalidate(id, true);
        return entity;
    	
    }
   @Override
	public void removeEntity(Object entityId) {
		super.removeEntity(entityId);
		cachingSupport.invalidate(entityId	, true);
	}

    @Override
    public void flush() {
        cachingSupport.flush();
    }

    @Override
    public int getEntityCacheMaxSize() {
        return cachingSupport.getMaxCacheSize();
    }

    @Override
    public boolean isCacheEnabled() {
        return cachingSupport.isCacheEnabled();
    }

    @Override
    public void setCacheEnabled(final boolean cacheInUse) {
        cachingSupport.setCacheEnabled(cacheInUse);
    }

    @Override
    public boolean usesCache() {
        return cachingSupport.usesCache(null);
    }

    @Override
    public void setEntityCacheMaxSize(final int maxSize) {
        cachingSupport.setMaxCacheSize(maxSize);
    }

    @Override
    public boolean containsEntity(final EntityContainer<T> container, final Object entityId, final Filter filter) {
        return cachingSupport.containsEntity(container, entityId, filter);
    }

    @Override
    public List<Object> getAllEntityIdentifiers(final EntityContainer<T> container, final Filter filter,
            final List<SortBy> sortBy) {
        return cachingSupport.getAllEntityIdentifiers(container, filter, sortBy);
    }

    @Override
    public synchronized T getEntity(final EntityContainer<T> container, final Object entityId) {
        return cachingSupport.getEntity(container, entityId);
    }

    @Override
    public boolean isEntitiesDetached() {
        return isCacheEnabled() || super.isEntitiesDetached();
    }

    @Override
    public boolean isCloneCachedEntities() {
        return cachingSupport.isCloneCachedEntities();
    }

    @Override
    public void setCloneCachedEntities(final boolean clone)
            throws UnsupportedOperationException {
        cachingSupport.setCloneCachedEntities(clone);
    }

    @Override
    public int getEntityCount(final EntityContainer<T> container, final Filter filter) {
        return cachingSupport.getEntityCount(container, filter);
    }

    @Override
    public Object getEntityIdentifierAt(final EntityContainer<T> container, final Filter filter, final List<SortBy> sortBy,
            final int index) {
        return cachingSupport.getEntityIdentifierAt(container, filter, sortBy, index);
    }

    @Override
    public Object getFirstEntityIdentifier(final EntityContainer<T> container, final Filter filter, final List<SortBy> sortBy) {
        return cachingSupport.getFirstEntityIdentifier(container, filter, sortBy);
    }

    @Override
    public Object getLastEntityIdentifier(final EntityContainer<T> container, final Filter filter, final List<SortBy> sortBy) {
        return cachingSupport.getLastEntityIdentifier(container, filter, sortBy);
    }

    @Override
    public Object getNextEntityIdentifier(final EntityContainer<T> container, final Object entityId, final Filter filter,
            final List<SortBy> sortBy) {
        return cachingSupport.getNextEntityIdentifier(container, entityId, filter, sortBy);
    }

    @Override
    public Object getPreviousEntityIdentifier(final EntityContainer<T> container, final Object entityId, final Filter filter,
            final List<SortBy> sortBy) {
        return cachingSupport.getPreviousEntityIdentifier(container, entityId, filter,
                sortBy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.jpacontainer.EntityProvider#refresh()
     */
    @Override
    public void refresh() {
        cachingSupport.clear();
        super.refresh();
    }

   
}