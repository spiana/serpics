package com.serpics.core.service;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.data.Repository;
import com.serpics.core.session.SessionContext;

@Transactional(readOnly = true)
public abstract class AbstractEntityService<T, ID extends Serializable , CONTEXT extends SessionContext> extends AbstractService<CONTEXT>  implements
EntityService<T, ID> , SerpicsService<CONTEXT>{

    public abstract Repository<T, ID> getEntityRepository();

    @Override
    @Transactional
    public T create(final T entity) {
        return getEntityRepository().create(entity);
    }

    @Override
    @Transactional
    public T update(final T entity) {
        return getEntityRepository().update(entity);
    }

    @Override
    @Transactional
    public void delete(final T entity) {
        getEntityRepository().delete(entity);
    }

    @Override
    @Transactional
    public void delete(final ID id) {
        getEntityRepository().delete(id);
    }

    @Override
    public Page<T> findAll(final Pageable page) {
        return getEntityRepository().findAll(page);
    }

    @Override
    public List<T> findAll() {
        return getEntityRepository().findAll();
    }

    @Override
    public List<T> findAll(final Specification<T> spec, final Sort sort) {
        if (spec == null && sort == null)
            return findAll();

        if (sort == null)
            return getEntityRepository().findAll(spec);

        if (spec == null)
            return getEntityRepository().findAll(sort);

        return getEntityRepository().findAll(spec, sort);
    }

    @Override
    public List<T> findAll(final Specification<T> spec, final Pageable page) {
        if (spec == null)
            return findAll(page).getContent();

        final Page<T> res = getEntityRepository().findAll(spec, page);
        return res.getContent();
    }

    @Override
    public List<T> findByexample(final T example) {
    	
        return getEntityRepository()
                .findAll(where(getEntityRepository().makeSpecification(example)));
    }

    @Override
    public T findOne(final ID id) {
        return getEntityRepository().findOne(id);
    }

    @Override
    public T findOne(final Specification<T> spec) {
        return getEntityRepository().findOne(spec);
    }

    @Override
    public T findOne(final Specification<T> spec, final Sort sort, final int index) {
        final PageRequest singleResultPage = new PageRequest(index, 1, sort);
        final List<T> l = findAll(spec, singleResultPage);
        if (!l.isEmpty())
            return l.get(0);
        else
            return null;
    }

    @Override
    public void detach(final T entity) {
        getEntityRepository().detach(entity);
    }

}
