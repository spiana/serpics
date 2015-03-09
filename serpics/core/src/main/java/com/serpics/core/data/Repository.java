package com.serpics.core.data;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public interface Repository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>  {
	
	public <T> Specification<T> makeSpecification(final T example);
	public T findOne(Specification<T> spec , final Sort sort , int index );
	 
	public void detach(final T entity);
    public T create(T entity);
    public T update(T entity);
    
    public Specification<T> getBaseSpecification();
  
    public void  setEngine(CommerceEngine engine);
	public CommerceSessionContext getCurrentContext();
	
	public void setRepositoryIniziatializer(RepositoryInitializer inizializer);
	
	public Class<?> getDomainClass();
   
 }
