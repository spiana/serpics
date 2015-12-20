package com.vaadin.addon.jpacontainer.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.serpics.core.data.Repository;
import com.serpics.vaadin.ui.FilterConverter;
import com.vaadin.addon.jpacontainer.BatchableEntityProvider;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityManagerProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;
import com.vaadin.addon.jpacontainer.util.CollectionUtil;
import com.vaadin.data.Container.Filter;


public class EntityRepositoryProvider<T> extends MutableLocalEntityProvider<T> implements EntityManagerProvider  , BatchableEntityProvider<T>{
	 private static final Logger logger = LoggerFactory.getLogger(EntityRepositoryProvider.class);

    private Repository<T, Serializable> repository;
   
    public EntityRepositoryProvider(final Class<T> entityClass) {
        super(entityClass);
        LazyLoadingDelegate delegate = new CustomLazyLoadingDelegate();
	    delegate.setEntityProvider(this);
	    setLazyLoadingDelegate(delegate);
		CustomQueryModifierDelegate queryModifierDelegate = new CustomQueryModifierDelegate();
		queryModifierDelegate.setClassMetadata(entityClass);
		setQueryModifierDelegate(queryModifierDelegate);
    }
    
   

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    protected void runInTransaction(final Runnable operation) {
//    	TransactionTemplate transactiontemplate = new TransactionTemplate((PlatformTransactionManager) EngineFactory.getCurrentApplicationContext().getBean("transactionManager"));
//    	
//    	transactiontemplate.execute(new TransactionCallback() {
//
//			@Override
//			public Object doInTransaction(TransactionStatus arg0) {
//				 operation.run();
//				 return null;
//			}
//    		
//		});
//      //  super.runInTransaction(operation);
//    }

    
    @Override
    public T updateEntity(final T entity) {
        fireEntityProviderChangeEvent(new EntitiesUpdatedEvent<T>(this, entity));
        return getRepository().update(entity);
    }

    @Override
    public T addEntity(final T entity) {
    	fireEntityProviderChangeEvent(new EntitiesAddedEvent<T>(this, entity));
    	return getRepository().create(entity);
        
    }

    @Override
    public void removeEntity(final Object entityId) {
    	T entity = getRepository().findOne((Serializable)entityId);
    	if(entity != null){
    		getRepository().delete((Serializable)entityId);
    		fireEntityProviderChangeEvent(new EntitiesRemovedEvent(this, entity));
    	//	this.refresh();
    	}else{
    		logger.error("entity not found for id : {}" , entityId);
    	}
    }
    
    @Override
    public void updateEntityProperty(final Object entityId, final String propertyName, final Object propertyValue)
            throws IllegalArgumentException, RuntimeException {

        T entity = (T) repository.findOne((Serializable) entityId);
        if (entity != null) {
            getEntityClassMetadata().setPropertyValue(entity, propertyName, propertyValue);
            entity = repository.update(entity);
        } else {
            logger.error("could not find entity to update!");
        }
        fireEntityProviderChangeEvent(new EntitiesUpdatedEvent<T>(this, entity));
    }

    @Override
    public T refreshEntity(final T arg0) {
        return getRepository().refresh(arg0);
        
    }
    @Override
    public EntityManager getEntityManager() {
    	return repository.getEntityManager();
    }
   
	public Repository<T, Serializable> getRepository() {
		return repository;
	}

	public void setRepository(Repository<T, Serializable> repository) {
		Assert.notNull(repository, "repository must non be null !");
		this.repository = repository;
	
	}



	@Override
	public void batchUpdate(
			com.vaadin.addon.jpacontainer.BatchableEntityProvider.BatchUpdateCallback<T>Callback)
			throws UnsupportedOperationException {
			
			Callback.batchUpdate(this);
	}

	@Override
	protected int doGetEntityCount(EntityContainer<T> container, Filter filter) {
        String entityIdPropertyName = getEntityClassMetadata()
                .getIdentifierProperty().getName();

        CriteriaBuilder cb = doGetEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<T> root = query.from(getEntityClassMetadata().getMappedClass());

        tellDelegateQueryWillBeBuilt(container, cb, query);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (filter != null) {
            predicates.add(FilterConverter.convertFilter(filter, cb, root));
        }
        tellDelegateFiltersWillBeAdded(container, cb, query, predicates);
        if (!predicates.isEmpty()) {
            query.where(CollectionUtil.toArray(Predicate.class, predicates));
        }
        tellDelegateFiltersWereAdded(container, cb, query);

        if (getEntityClassMetadata().hasEmbeddedIdentifier()) {
            /*
             * Hibernate will generate SQL for "count(obj)" that does not run on
             * HSQLDB. "count(*)" works fine, but then EclipseLink won't work.
             * With this hack, this method should work with both Hibernate and
             * EclipseLink.
             */
        	query.select(cb.countDistinct(root.get(entityIdPropertyName)));
            query.select(cb.countDistinct(root.get(entityIdPropertyName).get(
                    getEntityClassMetadata().getIdentifierProperty()
                            .getTypeMetadata().getPersistentPropertyNames()
                            .iterator().next())));
        	
        } else {
            query.select(cb.countDistinct(root.get(entityIdPropertyName)));
        }
        
        tellDelegateQueryHasBeenBuilt(container, cb, query);
        TypedQuery<Long> tq = doGetEntityManager().createQuery(query);
        return tq.getSingleResult().intValue();
    }
	
	@Override
	protected boolean doContainsEntity(EntityContainer<T> container,
            Object entityId, Filter filter) {
        assert entityId != null : "entityId must not be null";
        String entityIdPropertyName = getEntityClassMetadata()
                .getIdentifierProperty().getName();

        CriteriaBuilder cb = doGetEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<T> root = query.from(getEntityClassMetadata().getMappedClass());

        tellDelegateQueryWillBeBuilt(container, cb, query);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get(entityIdPropertyName),
                cb.literal(entityId)));
        if (filter != null) {
            predicates.add(FilterConverter.convertFilter(filter, cb, root));
        }
        tellDelegateFiltersWillBeAdded(container, cb, query, predicates);
        if (!predicates.isEmpty()) {
            query.where(CollectionUtil.toArray(Predicate.class, predicates));
        }
        tellDelegateFiltersWereAdded(container, cb, query);

        if (getEntityClassMetadata().hasEmbeddedIdentifier()) {
            /*
             * Hibernate will generate SQL for "count(obj)" that does not run on
             * HSQLDB. "count(*)" works fine, but then EclipseLink won't work.
             * With this hack, this method should work with both Hibernate and
             * EclipseLink.
             */
        	query.select(cb.countDistinct(root.get(entityIdPropertyName)));
            query.select(cb.countDistinct(root.get(entityIdPropertyName).get(
                    getEntityClassMetadata().getIdentifierProperty()
                            .getTypeMetadata().getPersistentPropertyNames()
                            .iterator().next())));
        	
        } else {

            query.select(cb.countDistinct(root.get(entityIdPropertyName)));
        }
        tellDelegateQueryHasBeenBuilt(container, cb, query);
        TypedQuery<Long> tq = doGetEntityManager().createQuery(query);
        return tq.getSingleResult() == 1;
    }

	 private void tellDelegateQueryWillBeBuilt(EntityContainer<T> container,
	            CriteriaBuilder cb, CriteriaQuery<?> query) {
	        if (getQueryModifierDelegate() != null) {
	        	getQueryModifierDelegate().queryWillBeBuilt(cb, query);
	        } else if (container.getQueryModifierDelegate() != null) {
	            container.getQueryModifierDelegate().queryWillBeBuilt(cb, query);
	        }
	    }

	    private void tellDelegateQueryHasBeenBuilt(EntityContainer<T> container,
	            CriteriaBuilder cb, CriteriaQuery<?> query) {
	        if (getQueryModifierDelegate() != null) {
	        	getQueryModifierDelegate().queryHasBeenBuilt(cb, query);
	        } else if (container.getQueryModifierDelegate() != null) {
	            container.getQueryModifierDelegate().queryHasBeenBuilt(cb, query);
	        }
	    }

	    private void tellDelegateFiltersWillBeAdded(EntityContainer<T> container,
	            CriteriaBuilder cb, CriteriaQuery<?> query,
	            List<Predicate> predicates) {
	        if (getQueryModifierDelegate() != null) {
	        	getQueryModifierDelegate().filtersWillBeAdded(cb, query, predicates);
	        } else if (container.getQueryModifierDelegate() != null) {
	            container.getQueryModifierDelegate().filtersWillBeAdded(cb, query,
	                    predicates);
	        }
	    }

	    private void tellDelegateFiltersWereAdded(EntityContainer<T> container,
	            CriteriaBuilder cb, CriteriaQuery<?> query) {
	        if (getQueryModifierDelegate() != null) {
	        	getQueryModifierDelegate().filtersWereAdded(cb, query);
	        } else if (container.getQueryModifierDelegate() != null) {
	            container.getQueryModifierDelegate().filtersWereAdded(cb, query);
	        }
	    }

	    private void tellDelegateOrderByWillBeAdded(EntityContainer<T> container,
	            CriteriaBuilder cb, CriteriaQuery<?> query, List<Order> orderBy) {
	        if (getQueryModifierDelegate() != null) {
	        	getQueryModifierDelegate().orderByWillBeAdded(cb, query, orderBy);
	        } else if (container.getQueryModifierDelegate() != null) {
	            container.getQueryModifierDelegate().orderByWillBeAdded(cb, query,
	                    orderBy);
	        }
	    }

	    private void tellDelegateOrderByWereAdded(EntityContainer<T> container,
	            CriteriaBuilder cb, CriteriaQuery<?> query) {
	        if (getQueryModifierDelegate() != null) {
	        	getQueryModifierDelegate().orderByWasAdded(cb, query);
	        } else if (container.getQueryModifierDelegate() != null) {
	            container.getQueryModifierDelegate().orderByWasAdded(cb, query);
	        }
	    }

}
