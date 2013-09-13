package com.serpics.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityManagerProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;
import com.vaadin.addon.jpacontainer.MutableEntityProvider;
import com.vaadin.addon.jpacontainer.QueryModifierDelegate;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.addon.jpacontainer.filter.util.FilterConverter;
import com.vaadin.data.Container.Filter;

public class SerpicsEntityProvider<T> implements MutableEntityProvider<T>{

	private EntityService service;
	private static final Logger logger = LoggerFactory.getLogger("SerpicsEntityProvider");
	
	@Override
	public T getEntity(EntityContainer<T> entityContainer, Object entityId) {
		 return doGetEntity(entityId);
	}

    protected T doGetEntity(Object entityId) {
        assert entityId != null : "entityId must not be null";
        assert  entityId.getClass().isAssignableFrom(Serializable.class) : "object IDs should be serializable";
       
        return (T) service.findOne((Serializable) entityId);
    }
    
	@Override
	public boolean isEntitiesDetached() {
		return true;
	}

	@Override
	public void setEntitiesDetached(boolean detached)
			throws UnsupportedOperationException {		
		
	}

	@Override
	public Object getEntityIdentifierAt(EntityContainer<T> entityContainer,
			Filter filter, List<SortBy> sortBy, int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
    protected Object doGetEntityIdentifierAt(EntityContainer<T> container,
            Filter filter, List<SortBy> sortBy, int index) {
        if (sortBy == null) {
            sortBy = Collections.emptyList();
        }
//        TypedQuery<Object> query = createFilteredQuery(container,
//                Arrays.asList(getEntityClassMetadata().getIdentifierProperty()
//                        .getName()), filter, addPrimaryKeyToSortList(sortBy),
//                false);
//        query.setMaxResults(1);
//        query.setFirstResult(index);
//        List<?> result = query.getResultList();
//        if (result.isEmpty()) {
//            return null;
//        } else {
//            return result.get(0);
//        }
        
     Object res = service.findOne(getSpecificationFromFilter(filter),
        		getSortFromSortBy(sortBy), index);
     
        return new Object();
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
	public Object getFirstEntityIdentifier(EntityContainer<T> entityContainer,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getLastEntityIdentifier(EntityContainer<T> entityContainer,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getNextEntityIdentifier(EntityContainer<T> entityContainer,
			Object entityId, Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPreviousEntityIdentifier(
			EntityContainer<T> entityContainer, Object entityId, Filter filter,
			List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAllEntityIdentifiers(
			EntityContainer<T> entityContainer, Filter filter,
			List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsEntity(EntityContainer<T> entityContainer,
			Object entityId, Filter filter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEntityCount(EntityContainer<T> entityContainer, Filter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQueryModifierDelegate(QueryModifierDelegate delegate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QueryModifierDelegate getQueryModifierDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIdentifier(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T refreshEntity(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityManagerProvider(
			EntityManagerProvider entityManagerProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityManagerProvider getEntityManagerProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLazyLoadingDelegate(LazyLoadingDelegate delegate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LazyLoadingDelegate getLazyLoadingDelegate() {
		// TODO Auto-generated method stub
		return null;
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

}
