package com.serpics.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface  EntityService<T , ID extends Serializable> {

    public T create(T entity);
    public T update(T entity);
    public void delete(T entity);

    public void delete(ID id);

    @Deprecated
    public Page<T> findAll(Pageable page);
    @Deprecated
    public List<T> findAll();
    @Deprecated
    public List<T> findAll(Specification<T> spec, Sort sort);
    @Deprecated
    public List<T> findAll(Specification<T> spec, Pageable page);
    
    @Deprecated
    public List<T> findByexample(T example);	

    public T findOne(ID id);
    @Deprecated
    public T findOne(Specification<T> spec, Sort sort, int index);

    @Deprecated
    public T findOne(Specification<T> spec);
    
    public T findByUUID(String uuid);

    @Deprecated
    public void detach(T entity);
}
