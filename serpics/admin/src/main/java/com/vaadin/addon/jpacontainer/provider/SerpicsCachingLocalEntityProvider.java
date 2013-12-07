package com.vaadin.addon.jpacontainer.provider;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.vaadin.addon.jpacontainer.CachingEntityProvider;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.LocalEntityProvider;
import com.vaadin.data.Container.Filter;

/**
 * En extended version of {@link LocalEntityProvider} that also implements the
 * {@link CachingEntityProvider} interface.
 * <p>
 * This provider can be used in applications in the same manner as
 * {@link LocalEntityProvider}, with a few exceptions. By default the cache is
 * on. The cache can be turned off using {@link #setCacheEnabled(boolean) }, in
 * which case the provider effectively works as a {@link LocalEntityProvider}.
 * <p>
 * If you are going to edit the entities returned by the container, you should
 * check the {@link #setCloneCachedEntities(boolean) } before continuing.
 * 
 * @author Petter Holmstr��m (Vaadin Ltd)
 * @since 1.0
 */
public class SerpicsCachingLocalEntityProvider<T> extends SerpicsEntityProvider<T>
        implements CachingEntityProvider<T> {

    // TODO Check how well caching works with concurrent users
    // Maybe some of the collections/maps should be replaced with
    // concurrent implementations? What about synchronization?

    private static final long serialVersionUID = 302600441430870363L;
    private SerpicsCachingSupport<T> cachingSupport;
    
    /**
     * Creates a new <code>CachingLocalEntityProvider</code>. The entity manager
     * must be set using
     * {@link #setEntityManager(javax.persistence.EntityManager) }.
     * 
     * @param entityClass
     *            the entity class (must not be null).
     */
    public SerpicsCachingLocalEntityProvider(Class<T> entityClass) {
        super(entityClass);
        cachingSupport = new SerpicsCachingSupport<T>(this);
    }

    
    
    
    
    @Override
    public void updateEntityProperty(Object entityId, String propertyName,
            Object propertyValue) throws IllegalArgumentException {
    	super.updateEntityProperty(entityId, propertyName, propertyValue);
        cachingSupport.invalidate(entityId, true);
    }

    
    @Override
    public T addEntity(T entity) {
    	super.addEntity(entity);
    	cachingSupport.entityAdded(entity);
    	return entity;
    }
    

    public void flush() {
        cachingSupport.flush();
    }

    public int getEntityCacheMaxSize() {
        return cachingSupport.getMaxCacheSize();
    }

    public boolean isCacheEnabled() {
        return cachingSupport.isCacheEnabled();
    }

    public void setCacheEnabled(boolean cacheInUse) {
        cachingSupport.setCacheEnabled(cacheInUse);
    }

    public boolean usesCache() {
        return cachingSupport.usesCache(null);
    }

    public void setEntityCacheMaxSize(int maxSize) {
        cachingSupport.setMaxCacheSize(maxSize);
    }

    @Override
    public boolean containsEntity(EntityContainer<T> container, Object entityId, Filter filter) {
        return cachingSupport.containsEntity(container, entityId, filter);
    }

    @Override
    public List<Object> getAllEntityIdentifiers(EntityContainer<T> container, Filter filter,
            List<SortBy> sortBy) {
        return cachingSupport.getAllEntityIdentifiers(container, filter, sortBy);
    }

    @Override
    public synchronized T getEntity(EntityContainer<T> container, Object entityId) {
        return cachingSupport.getEntity(container, entityId);
    }

    @Override
    public boolean isEntitiesDetached() {
        return isCacheEnabled() || super.isEntitiesDetached();
    }

    public boolean isCloneCachedEntities() {
        return cachingSupport.isCloneCachedEntities();
    }

    public void setCloneCachedEntities(boolean clone)
            throws UnsupportedOperationException {
        cachingSupport.setCloneCachedEntities(clone);
    }

    @Override
    public int getEntityCount(EntityContainer<T> container, Filter filter) {
        return cachingSupport.getEntityCount(container, filter);
    }

    @Override
    public Object getEntityIdentifierAt(EntityContainer<T> container, Filter filter, List<SortBy> sortBy,
            int index) {
        return cachingSupport.getEntityIdentifierAt(container, filter, sortBy, index);
    }

    @Override
    public Object getFirstEntityIdentifier(EntityContainer<T> container, Filter filter, List<SortBy> sortBy) {
        return cachingSupport.getFirstEntityIdentifier(container, filter, sortBy);
    }

    @Override
    public Object getLastEntityIdentifier(EntityContainer<T> container, Filter filter, List<SortBy> sortBy) {
        return cachingSupport.getLastEntityIdentifier(container, filter, sortBy);
    }

    @Override
    public Object getNextEntityIdentifier(EntityContainer<T> container, Object entityId, Filter filter,
            List<SortBy> sortBy) {
        return cachingSupport.getNextEntityIdentifier(container, entityId, filter, sortBy);
    }

    @Override
    public Object getPreviousEntityIdentifier(EntityContainer<T> container, Object entityId, Filter filter,
            List<SortBy> sortBy) {
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
    
    public T refreshEntity(T entity) {
        cachingSupport.clear();
        return super.refreshEntity(entity);
    };
}