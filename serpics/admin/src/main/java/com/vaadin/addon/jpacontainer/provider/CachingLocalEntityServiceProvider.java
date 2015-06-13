package com.vaadin.addon.jpacontainer.provider;

import java.util.List;

import com.vaadin.addon.jpacontainer.CachingEntityProvider;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.data.Container.Filter;

public class  CachingLocalEntityServiceProvider<T> extends EntityServiceProvider<T>
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
    }

    @Override
    public T refreshEntity(final T entity) {
        cachingSupport.clear();
        return super.refreshEntity(entity);
    };
}