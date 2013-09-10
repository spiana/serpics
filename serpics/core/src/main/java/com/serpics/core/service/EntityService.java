package com.serpics.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  EntityService<T , ID> {

	public T create(T entity);
	public void delete(T entity);
	public Page<T> findAll(Pageable page);
	public List<T> findAll();
	public T update(T entity);
	public List<T> findByexample(T example);
	public T findOne(ID id);
}
