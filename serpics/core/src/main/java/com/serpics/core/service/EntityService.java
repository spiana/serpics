package com.serpics.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  EntityService<T , ID extends Serializable> {

    public T create(T entity);
    public T update(T entity);
    public void delete(T entity);

    public void delete(ID id);

   
    public Page<T> findAll(Pageable page);
    public List<T> findAll();

    public T findOne(ID id);

    public T findByUUID(String uuid);

    @Deprecated
    public void detach(T entity);
}
