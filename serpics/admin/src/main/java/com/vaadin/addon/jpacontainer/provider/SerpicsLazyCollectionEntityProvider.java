package com.vaadin.addon.jpacontainer.provider;

import java.util.List;

import javax.persistence.EntityManager;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.data.Container.Filter;

public class SerpicsLazyCollectionEntityProvider<T> extends MutableLocalEntityProvider<T> {

	public SerpicsLazyCollectionEntityProvider(Class<T> entityClass) {
		super(entityClass);		
	}

	@Override
	public void setTransactionsHandledByProvider(boolean transactionsHandled) {
		// TODO Auto-generated method stub
		super.setTransactionsHandledByProvider(transactionsHandled);
	}

	@Override
	public boolean isTransactionsHandledByProvider() {
		// TODO Auto-generated method stub
		return super.isTransactionsHandledByProvider();
	}

	@Override
	public T addEntity(T entity) {
		// TODO Auto-generated method stub
		return super.addEntity(entity);
	}

	@Override
	public void removeEntity(Object entityId) {
		// TODO Auto-generated method stub
		super.removeEntity(entityId);
	}

	@Override
	public T updateEntity(T entity) {
		// TODO Auto-generated method stub
		return super.updateEntity(entity);
	}

	@Override
	public void updateEntityProperty(Object entityId, String propertyName,
			Object propertyValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		super.updateEntityProperty(entityId, propertyName, propertyValue);
	}

	@Override
	protected EntityManager doGetEntityManager() throws IllegalStateException {
		// TODO Auto-generated method stub
		return super.doGetEntityManager();
	}

	@Override
	protected boolean doContainsEntity(EntityContainer<T> container,
			Object entityId, Filter filter) {
		// TODO Auto-generated method stub
		return super.doContainsEntity(container, entityId, filter);
	}

	@Override
	public boolean containsEntity(EntityContainer<T> container,
			Object entityId, Filter filter) {
		// TODO Auto-generated method stub
		return super.containsEntity(container, entityId, filter);
	}

	@Override
	protected T doGetEntity(Object entityId) {
		// TODO Auto-generated method stub
		return super.doGetEntity(entityId);
	}

	@Override
	public T getEntity(EntityContainer<T> container, Object entityId) {
		// TODO Auto-generated method stub
		return super.getEntity(container, entityId);
	}

	@Override
	protected Object doGetEntityIdentifierAt(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy, int index) {
		// TODO Auto-generated method stub
		return super.doGetEntityIdentifierAt(container, filter, sortBy, index);
	}

	@Override
	public Object getEntityIdentifierAt(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy, int index) {
		// TODO Auto-generated method stub
		return super.getEntityIdentifierAt(container, filter, sortBy, index);
	}

	@Override
	protected int doGetEntityCount(EntityContainer<T> container, Filter filter) {
		// TODO Auto-generated method stub
		return super.doGetEntityCount(container, filter);
	}

	@Override
	public int getEntityCount(EntityContainer<T> container, Filter filter) {
		// TODO Auto-generated method stub
		return super.getEntityCount(container, filter);
	}

	@Override
	protected Object doGetFirstEntityIdentifier(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.doGetFirstEntityIdentifier(container, filter, sortBy);
	}

	@Override
	public Object getFirstEntityIdentifier(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.getFirstEntityIdentifier(container, filter, sortBy);
	}

	@Override
	protected Object doGetLastEntityIdentifier(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.doGetLastEntityIdentifier(container, filter, sortBy);
	}

	@Override
	public Object getLastEntityIdentifier(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.getLastEntityIdentifier(container, filter, sortBy);
	}

	@Override
	protected Object doGetNextEntityIdentifier(EntityContainer<T> container,
			Object entityId, Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.doGetNextEntityIdentifier(container, entityId, filter, sortBy);
	}

	@Override
	public Object getNextEntityIdentifier(EntityContainer<T> container,
			Object entityId, Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.getNextEntityIdentifier(container, entityId, filter, sortBy);
	}

	@Override
	protected Object doGetPreviousEntityIdentifier(
			EntityContainer<T> container, Object entityId, Filter filter,
			List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.doGetPreviousEntityIdentifier(container, entityId, filter, sortBy);
	}

	@Override
	public Object getPreviousEntityIdentifier(EntityContainer<T> container,
			Object entityId, Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.getPreviousEntityIdentifier(container, entityId, filter, sortBy);
	}

	@Override
	protected List<Object> doGetAllEntityIdentifiers(
			EntityContainer<T> container, Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.doGetAllEntityIdentifiers(container, filter, sortBy);
	}

	@Override
	public List<Object> getAllEntityIdentifiers(EntityContainer<T> container,
			Filter filter, List<SortBy> sortBy) {
		// TODO Auto-generated method stub
		return super.getAllEntityIdentifiers(container, filter, sortBy);
	}

	@Override
	public Object getIdentifier(T entity) {
		// TODO Auto-generated method stub
		return super.getIdentifier(entity);
	}

	@Override
	public T refreshEntity(T entity) {
		// TODO Auto-generated method stub
		return super.refreshEntity(entity);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		super.refresh();
	}
	
	

}
