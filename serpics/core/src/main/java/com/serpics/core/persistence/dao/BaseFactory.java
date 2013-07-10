package com.serpics.core.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface BaseFactory{

	public Object persist(Object entity);
	public void delete(Object entity);
	public Object merge(Object entity);
	public <T> Object  find(Class<T> entityClass , Object id);
	public <T> List<T> findByExample(Class<T> clazz , T example) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException ;

}
