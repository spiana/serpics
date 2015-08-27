package com.serpics.core.data;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;


public interface Repository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>  {
	
	public <T> Specification<T> makeSpecification(final T example);
//	public T findOne(Specification<T> spec , final Sort sort , int index );
	public T refresh(T entity);
	
	public void detach(final T entity);
	@Deprecated
	public T create(T entity);
	@Deprecated
	public T update(T entity);
    
    //public Specification<T> getDefaultSpecification();
  
    public void  setEngine(Engine<SessionContext> engine);
	public void setRepositoryIniziatializer(RepositoryInitializer inizializer);
	public CommerceSessionContext getCurrentContext();

	
	public Class<?> getDomainClass();
	public EntityManager getEntityManager();
	
	public T findByUUID(String uuid);
   
 }
