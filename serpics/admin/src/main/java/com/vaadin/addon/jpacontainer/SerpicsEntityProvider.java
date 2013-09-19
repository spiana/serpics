package com.vaadin.addon.jpacontainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.core.service.EntityService;
import com.vaadin.addon.jpacontainer.CachingEntityProvider;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityManagerProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;
import com.vaadin.addon.jpacontainer.MutableEntityProvider;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.addon.jpacontainer.filter.util.FilterConverter;
import com.vaadin.addon.jpacontainer.metadata.EntityClassMetadata;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.data.Container.Filter;

public class SerpicsEntityProvider<T> extends CachingLocalEntityProvider<T> implements MutableEntityProvider<T>, CachingEntityProvider<T>, EntityManagerProvider{

	private EntityService service;
	
	private Class<T> entityClass;
	private EntityClassMetadata<T> entityClassMetadata;
	
	private LazyLoadingDelegate lazyLoadingDelegate;
	private EntityManagerFactory emf;
	
	private static final Logger logger = LoggerFactory.getLogger("SerpicsEntityProvider");
	
	
	
	public SerpicsEntityProvider(Class<T> entityClass){
		super(entityClass);		
//		setEntityCacheMaxSize(5000);
//		this.entityClass = entityClass;
		this.entityClassMetadata = super.getEntityClassMetadata();
		
	}

	
	


    protected T doGetEntity(Object entityId) {
        assert entityId != null : "entityId must not be null";
        assert  entityId.getClass().isAssignableFrom(Serializable.class) : "object IDs should be serializable";
       
        return (T) service.findOne((Serializable) entityId);
    }
    


	
    protected Object doGetEntityIdentifierAt(EntityContainer<T> container,
            Filter filter, List<SortBy> sortBy, int index) {
    	Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty())
        	sort = getSortFromSortBy(sortBy);
        
        Specification spec = null;
        if (filter != null)
        	spec = getSpecificationFromFilter(filter);
           
        	
     Object res = service.findOne(spec, sort, index);
  
     T entity = (T) res;
     
     
     Object id = entityClassMetadata.getPropertyValue(entity, entityClassMetadata.getIdentifierProperty().getName());
     
     return id;
    }
    

    protected static Specification getSpecificationFromFilter(final Filter filter){
    	
    	return new Specification() {

			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query,
					CriteriaBuilder cb) {
				return FilterConverter.convertFilter(filter, cb, root);
			}
		};
    }
    
    protected static Sort getSortFromSortBy(final List<SortBy> sortBy){
    	List<Order> orders = new ArrayList<Sort.Order>();
    	for (SortBy in : sortBy){    		
    		Order order = new Order( in.isAscending() ? Direction.ASC : Direction.DESC, in.getPropertyId().toString());
    		orders.add(order);
    	}
    	Sort s = new Sort(orders);
    	return s;
    }

	@Override
	public Object doGetFirstEntityIdentifier(EntityContainer<T> entityContainer,
			Filter filter, List<SortBy> sortBy) {
        
		Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty())
        	sort = getSortFromSortBy(sortBy);
        
        Specification spec = null;
        if (filter != null)
        	spec = getSpecificationFromFilter(filter);
        
     Object res = service.findOne(spec,
    		 sort, 0);
     
     T entity = (T) res;
     
     
     Object id = entityClassMetadata.getPropertyValue(entity, entityClassMetadata.getIdentifierProperty().getName());
     
     return id;
	}

	
	
	@Override
	public Object doGetLastEntityIdentifier(EntityContainer<T> entityContainer,
			Filter filter, List<SortBy> sortBy) {
        
     List<T> res = findAll(filter, sortBy);
     
     T entity = (T) res.get(res.size() -1);
     
     
     Object id = entityClassMetadata.getPropertyValue(entity, entityClassMetadata.getIdentifierProperty().getName());
     
     return id;
		
	}

	
	
	@Override
	public Object doGetNextEntityIdentifier(EntityContainer<T> entityContainer,
			Object entityId, Filter filter, List<SortBy> sortBy) {
        
		List<T> res = findAll(filter, sortBy);		
		
		boolean found = false;
		for (T t : res){
			Object id = entityClassMetadata.getPropertyValue(t, entityClassMetadata.getIdentifierProperty().getName());
			if ( found )
				 return id;
			if (id.equals(entityId)) found = true;
			
		}
    
		return null;
     
     
     

	}

	
	
	@Override
	public Object doGetPreviousEntityIdentifier(
			EntityContainer<T> entityContainer, Object entityId, Filter filter,
			List<SortBy> sortBy) {
		
		List<T> res = findAll(filter, sortBy);
		
		Object last = null;
		for (T t : res){
			Object id = entityClassMetadata.getPropertyValue(t, entityClassMetadata.getIdentifierProperty().getName());
			
			if (id.equals(entityId))  return last;
			last = id;
			
		}
    
		return null;
	}

	
	private List<T> findAll(Filter filter,
			List<SortBy> sortBy){
		
		Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty())
        	sort = getSortFromSortBy(sortBy);
        
        Specification spec = null;
        if (filter != null)
        	spec = getSpecificationFromFilter(filter);
        
		List<T> res = service.findAll(spec,
        		sort);
		return res;
	}
	
	
	
	@Override
	public List<Object> doGetAllEntityIdentifiers(
			EntityContainer<T> entityContainer, Filter filter,
			List<SortBy> sortBy) {
		
		List<T> res = findAll(filter, sortBy);
		
		List<Object> ids = new ArrayList<Object>();
		for (T t : res){
			Object id = entityClassMetadata.getPropertyValue(t, entityClassMetadata.getIdentifierProperty().getName());
			
			ids.add(id);
			
		}
		
		return ids;
	}

	
	
	public List<Object> doGetAllEntityIdentifiers(
			EntityContainer<T> entityContainer, Filter filter,
			List<SortBy> sortBy, int startFrom, int fetchMax) {
		
		
		List<T> res = findAll(filter, sortBy);
		
		ArrayList<T> array = new ArrayList<T>(res);
		
		List<Object> ids = new ArrayList<Object>();
//		logger.error("startFrom:" + startFrom + ", fetchMax:" + fetchMax);
		
		for (int i = startFrom; i < Math.min(startFrom + fetchMax, res.size()); i++){
			T t = array.get(i);
			Object id = entityClassMetadata.getPropertyValue(t, entityClassMetadata.getIdentifierProperty().getName());
			
			ids.add(id);
			
		}
		
		return ids;
	}
	

	
	@Override
	public boolean doContainsEntity(EntityContainer<T> entityContainer,
			Object entityId, Filter filter) {
		
		
		
		
		SortBy sortBy = new SortBy(entityClassMetadata.getIdentifierProperty().getName(), true);
		List<T> res = findAll(filter, Arrays.asList(sortBy));
		
		
		for (T t : res){
			Object id = entityClassMetadata.getPropertyValue(t, entityClassMetadata.getIdentifierProperty().getName());
			
			if (id.equals(entityId))  return true;					
		}
    	
		return false;
	}

	
	
	@Override
	public int doGetEntityCount(EntityContainer<T> entityContainer, Filter filter) {
		Sort s = new Sort(entityClassMetadata.getIdentifierProperty().getName());
		
		List<T> res = null;
		
		if (filter != null){
			res = service.findAll(getSpecificationFromFilter(filter),
				s);
		} else {
			res = service.findAll();
		}
		
		
		return res.size();
	}

	
	

	@Override
	public Object getIdentifier(T entity) {
		Object id = entityClassMetadata.getPropertyValue(entity, entityClassMetadata.getIdentifierProperty().getName());
		return id;
	}

	@Override
	public T refreshEntity(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager doGetEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public void setEntityManagerProvider(
			EntityManagerProvider entityManagerProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityManagerProvider getEntityManagerProvider() {
		return this;
	}

	@Override
	public void setLazyLoadingDelegate(LazyLoadingDelegate delegate) {
		this.lazyLoadingDelegate = delegate;		
	}

	@Override
	public LazyLoadingDelegate getLazyLoadingDelegate() {
		return lazyLoadingDelegate;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T addEntity(T entity) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T updateEntity(T entity) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEntityProperty(Object entityId, String propertyName,
			Object propertyValue) throws IllegalArgumentException,
			RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntity(Object entityId) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	public EntityService getService() {
		return service;
	}

	public void setService(EntityService service) {
		this.service = service;
	}


	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}





	public EntityClassMetadata<T> getEntityClassMetadata() {
		return entityClassMetadata;
	}





	public void setEntityClassMetadata(EntityClassMetadata<T> entityClassMetadata) {
		this.entityClassMetadata = entityClassMetadata;
	}





}
