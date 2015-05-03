package com.vaadin.addon.jpacontainer.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.data.Repository;
import com.serpics.core.service.EntityService;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityManagerProvider;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.addon.jpacontainer.filter.util.FilterConverter;
import com.vaadin.data.Container.Filter;

@Transactional(readOnly=true)
public class EntityServiceProvider<T> extends MutableLocalEntityProvider<T> implements EntityManagerProvider {

   // private EntityService<T, Serializable> service;

    private Repository<T, Serializable> repository;
    
    // private EntityClassMetadata<T> entityClassMetadata;


    private static final Logger logger = LoggerFactory.getLogger("SerpicsEntityProvider");

    public EntityServiceProvider(final Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
    	return repository.getEntityManager();
    }
    
    protected Specification<T> getSpecificationFromFilter(final Filter filter) {

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
                return FilterConverter.convertFilter(filter, cb, root);
            }

        };
    }


    protected static Sort getSortFromSortBy(final List<SortBy> sortBy) {
        final List<Order> orders = new ArrayList<Sort.Order>();
        for (final SortBy in : sortBy) {
            final Order order = new Order(in.isAscending() ? Direction.ASC : Direction.DESC, in.getPropertyId()
                    .toString());
            orders.add(order);
        }
        final Sort s = new Sort(orders);
        return s;
    }

    private List<T> findAll(final Filter filter, final List<SortBy> sortBy) {

        Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty())
            sort = getSortFromSortBy(sortBy);

        Specification spec = null;
        if (filter != null)
            spec = getSpecificationFromFilter(filter);

        final List<T> res = repository.findAll(spec, sort);
        return res;
    }

    // Override LocalEntityProvider

    @Override
    protected T doGetEntity(final Object entityId) {
        assert entityId != null : "entityId must not be null";
        assert entityId.getClass().isAssignableFrom(Serializable.class) : "object IDs should be serializable";
        return (T) repository.findOne((Serializable) entityId);
    }

    @Override
    protected Object doGetEntityIdentifierAt(final EntityContainer<T> container, final Filter filter,
            final List<SortBy> sortBy, final int index) {
        Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty())
            sort = getSortFromSortBy(sortBy);

        Specification spec = null;
        if (filter != null)
            spec = getSpecificationFromFilter(filter);

        final Object res = repository.findOne(spec, sort, index);

        final T entity = (T) res;

        final Object id = getEntityClassMetadata().getPropertyValue(entity,
                getEntityClassMetadata().getIdentifierProperty()
                .getName());

        return id;
    }

    @Override
    public Object doGetFirstEntityIdentifier(final EntityContainer<T> entityContainer, final Filter filter,
            final List<SortBy> sortBy) {

        Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty())
            sort = getSortFromSortBy(sortBy);

        Specification spec = null;
        if (filter != null)
            spec = getSpecificationFromFilter(filter);

        final Object res = repository.findOne(spec, sort, 0);

        final T entity = (T) res;

        final Object id = getEntityClassMetadata().getPropertyValue(entity,
                getEntityClassMetadata().getIdentifierProperty()
                .getName());

        return id;
    }

    @Override
    public Object doGetLastEntityIdentifier(final EntityContainer<T> entityContainer, final Filter filter,
            final List<SortBy> sortBy) {

        final List<T> res = findAll(filter, sortBy);

        final T entity = (T) res.get(res.size() - 1);

        final Object id = getEntityClassMetadata().getPropertyValue(entity,
                getEntityClassMetadata().getIdentifierProperty()
                .getName());

        return id;

    }

    @Override
    public Object doGetNextEntityIdentifier(final EntityContainer<T> entityContainer, final Object entityId,
            final Filter filter, final List<SortBy> sortBy) {

        final List<T> res = findAll(filter, sortBy);

        boolean found = false;
        for (final T t : res) {
            final Object id = getEntityClassMetadata().getPropertyValue(t,
                    getEntityClassMetadata().getIdentifierProperty()
                    .getName());
            if (found)
                return id;
            if (id.equals(entityId))
                found = true;

        }

        return null;

    }

    @Override
    public Object doGetPreviousEntityIdentifier(final EntityContainer<T> entityContainer, final Object entityId,
            final Filter filter, final List<SortBy> sortBy) {

        final List<T> res = findAll(filter, sortBy);

        Object last = null;
        for (final T t : res) {
            final Object id = getEntityClassMetadata().getPropertyValue(t,
                    getEntityClassMetadata().getIdentifierProperty()
                    .getName());

            if (id.equals(entityId))
                return last;
            last = id;

        }

        return null;
    }

    @Override
    public List<Object> doGetAllEntityIdentifiers(final EntityContainer<T> entityContainer, final Filter filter,
            final List<SortBy> sortBy) {

        final List<T> res = findAll(filter, sortBy);

        final List<Object> ids = new ArrayList<Object>();
        for (final T t : res) {
            final Object id = getEntityClassMetadata().getPropertyValue(t,
                    getEntityClassMetadata().getIdentifierProperty()
                    .getName());

            ids.add(id);

        }

        return ids;
    }

    public List<Object> doGetAllEntityIdentifiers(final EntityContainer<T> entityContainer, final Filter filter,
            final List<SortBy> sortBy, final int startFrom, final int fetchMax) {

        final List<T> res = findAll(filter, sortBy);

        final ArrayList<T> array = new ArrayList<T>(res);

        final List<Object> ids = new ArrayList<Object>();
        // logger.error("startFrom:" + startFrom + ", fetchMax:" + fetchMax);

        for (int i = startFrom; i < Math.min(startFrom + fetchMax, res.size()); i++) {
            final T t = array.get(i);
            final Object id = getEntityClassMetadata().getPropertyValue(t,
                    getEntityClassMetadata().getIdentifierProperty()
                    .getName());

            ids.add(id);

        }

        return ids;
    }

    @Override
    public boolean doContainsEntity(final EntityContainer<T> entityContainer, final Object entityId, final Filter filter) {

        final SortBy sortBy = new SortBy(getEntityClassMetadata().getIdentifierProperty().getName(), true);
        final List<T> res = findAll(filter, Arrays.asList(sortBy));

        for (final T t : res) {
            final Object id = getEntityClassMetadata().getPropertyValue(t,
                    getEntityClassMetadata().getIdentifierProperty()
                    .getName());

            if (id.equals(entityId))
                return true;
        }

        return false;
    }

    @Override
    public int doGetEntityCount(final EntityContainer<T> entityContainer, final Filter filter) {
        final Sort s = new Sort(getEntityClassMetadata().getIdentifierProperty().getName());

        List<T> res = null;

        if (filter != null) {
            res = repository.findAll(getSpecificationFromFilter(filter), s);
        } else {
            res = repository.findAll();
        }

        return res.size();
    }

    @Override
    public Object getIdentifier(final T entity) {
        final Object id = getEntityClassMetadata().getPropertyValue(entity, getEntityClassMetadata().getIdentifierProperty()
                .getName());
        return id;
    }

    @Override
    public void setEntityManagerProvider(final EntityManagerProvider entityManagerProvider) {
        // TODO Auto-generated method stub

    }

    @Override
    public EntityManagerProvider getEntityManagerProvider() {
        return this;
    }

    @Override
    public T addEntity(final T entity) {
        final T _entity = repository.create(entity);
        // service.detach(_entity);
        fireEntityProviderChangeEvent(new EntitiesAddedEvent<T>(this, (T) _entity));
        return _entity;
    }

    @Override
    public T updateEntity(final T entity) {
        final T _entity = repository.update(entity);
        // service.detach(_entity);
        fireEntityProviderChangeEvent(new EntitiesUpdatedEvent<T>(this, _entity));
        return _entity;
    }

    @Override
    public void updateEntityProperty(final Object entityId, final String propertyName, final Object propertyValue)
            throws IllegalArgumentException, RuntimeException {

        T entity = (T) repository.findOne((Serializable) entityId);
        if (entity != null) {
            getEntityClassMetadata().setPropertyValue(entity, propertyName, propertyValue);
            entity = repository.update(entity);
            // service.detach(entity);
        } else {
            logger.error("could not find entity to update!");
        }
        fireEntityProviderChangeEvent(new EntitiesUpdatedEvent<T>(this, entity));

    }

    @Override
    public void removeEntity(final Object entityId) {
        final T _entity = getRepository().findOne((Serializable) entityId);
        if (_entity != null) {
        	getRepository().delete(_entity);
            repository.detach(_entity);
            // fireEntityProviderChangeEvent(new EntitiesRemovedEvent<T>(this, _entity));
            // FIXME : to fix firing event !
            refresh();
        }
    }

    @Override
    public T refreshEntity(final T arg0) {
        getRepository().refresh(arg0);
        return arg0;
    }

	public Repository<T, Serializable> getRepository() {
		return repository;
	}

	public void setRepository(Repository<T, Serializable> repository) {
		Assert.notNull(repository, "repository must non be null !");
		this.repository = repository;
	}

   

   

}
