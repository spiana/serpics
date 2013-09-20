package com.serpics.core.service;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.core.data.Repository;

public abstract class AbstractEntityService<T, ID extends Serializable> extends AbstractService implements EntityService<T, ID>{


	public abstract Repository<T, ID> getEntityRepository();
	public abstract Specification<T> getBaseSpec();
	
	@Override
	public T create(T entity){
		return getEntityRepository().saveAndFlush(entity);
	}
	
	@Override
	public T update(T entity){
		return getEntityRepository().saveAndFlush(entity);
	}
	
	@Override
	public void delete(T entity) {
		getEntityRepository().delete(entity);
	}
	

	@Override
	public Page<T> findAll(Pageable page) {
		return getEntityRepository().findAll(getBaseSpec(), page);
	}
	

	@Override
	public List<T> findAll() {
		return getEntityRepository().findAll(getBaseSpec());
	}	

	
	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		if (spec == null && sort == null) return findAll();
		
		if (sort == null)
			return getEntityRepository().findAll( where(spec).and(getBaseSpec()));
		
		if (spec == null)
			return getEntityRepository().findAll( where(getBaseSpec()), sort );
		
		return getEntityRepository().findAll( where(spec).and(getBaseSpec()), sort);
	}

	
	@Override
	public List<T> findAll(Specification<T> spec, Pageable page) {
		if (spec == null)
			return findAll(page).getContent();
		
		Page<T> res = getEntityRepository().findAll( where(spec).and(getBaseSpec()), page);
		return res.getContent();
	}

	
	@Override
	public List<T> findByexample(T example) {
		return getEntityRepository().findAll(where(getEntityRepository()
				.makeSpecification(example)));
	}

	
	@Override
	public T findOne(ID id) {
		return getEntityRepository().findOne(id);
	}
	
	
	@Override
	public T findOne(Specification<T> spec, Sort sort, int index) {
		final PageRequest singleResultPage = new PageRequest(index, 1, sort);
		List<T> l = findAll(spec, singleResultPage);
		if (!l.isEmpty())
			return l.get(0);
		else return null;
	}


}
