package com.vaadin.addon.jpacontainer.provider;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.serpics.core.data.Repository;
import com.vaadin.addon.jpacontainer.BatchableEntityProvider;
import com.vaadin.addon.jpacontainer.EntityManagerProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;


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

	
}
