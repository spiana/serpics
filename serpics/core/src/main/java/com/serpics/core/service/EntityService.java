package com.serpics.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface  EntityService<T , ID extends Serializable> {

	public T create(T entity);
	public void delete(T entity);
	public Page<T> findAll(Pageable page);
	public List<T> findAll();
	public List<T> findAll(Specification<T> spec, Sort sort);
	public List<T> findAll(Specification<T> spec, Pageable page);
	public T findOne(Specification<T> spec, Sort sort, int index);
	public T update(T entity);
	public List<T> findByexample(T example);
	public T findOne(ID id);
}
