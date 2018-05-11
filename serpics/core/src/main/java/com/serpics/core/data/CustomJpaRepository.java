/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.core.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true , isolation = Isolation.DEFAULT)
public class CustomJpaRepository<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID>  implements JpaSpecificationExecutor<T>, SerpicsJpaRepository {

	Logger LOG = LoggerFactory.getLogger(CustomJpaRepository.class);
	
	private RepositoryInitializer initializer;

	private EntityInformation< T, ?> entityInformation;
	private final InterceptorManager<T> interceptorMapping;
	private EntityManager entityManager;
	
	
	public CustomJpaRepository(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
		interceptorMapping = new InterceptorManager<T>();
		
	}
	public CustomJpaRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		interceptorMapping = new InterceptorManager<T>();
	}

	 protected TypedQuery<Long> getCountQuery(Specification<T> spec){
		return super.getCountQuery(getMergedSpecification(spec));
	 }
	 
	 private <S extends T> Specification<S> getMergedSpecification(Specification<S> spec) {
		 
		 List<Specification> defaultSpecs = initializer
					.getSpecificationForClass(getDomainClass());

			if (defaultSpecs != null && !defaultSpecs.isEmpty()){
				if(LOG.isDebugEnabled())
					LOG.debug("Found {} defult specification for class {}" , defaultSpecs.size() , getDomainClass());
				
				if (spec != null){
					spec = Specifications.where(spec);
					for (Specification specification : defaultSpecs) {
						if (LOG.isDebugEnabled())
							LOG.debug("applied specification {} !" , specification.getClass().getName());
						spec = ((Specifications<T>) spec).and(specification);
					}	
				}else{
					if (defaultSpecs.size() == 1){
						if (LOG.isDebugEnabled())
							LOG.debug("applied specification {} !" , defaultSpecs.get(0).getClass().getName());
						spec = Specifications.where(defaultSpecs.get(0));
						
					}else{
						if (LOG.isDebugEnabled())
							LOG.debug("applied specification {} !" , defaultSpecs.get(0).getClass().getName());
						spec = Specifications.where(defaultSpecs.get(0));
					
						for (int x=1 ; x < defaultSpecs.size() ; x++){
							if (LOG.isDebugEnabled())
								LOG.debug("applied specification {} !" , defaultSpecs.get(x).getClass().getName());
							spec =((Specifications<T>) spec).and(defaultSpecs.get(x));
						}
					}
						
				}
			}
			return spec;
	 }
	
	@Override
	public void setRepositoryIniziatializer(RepositoryInitializer inizializer) {
		this.initializer = inizializer;
	}

	@Transactional
	@Override
	public <S extends T> S save(S entity) {
		boolean isnew = false;
		// if entity is not new merge it
		if (entityInformation.isNew(entity)){
			isnew =true;
		}
		if (isnew){
			interceptorMapping.performBeforeSaveInterceptor(entity);
		}
		else{
			entity = entityManager.merge(entity);
			interceptorMapping.performBeforeUpdateInterceptor(entity);
		}
		entity = super.save(entity);
		
		if(isnew)
			interceptorMapping.performAfterSaveInterceptor(entity);
		else
			interceptorMapping.performAfterUpdateInterceptor(entity);
		
		return entity;
	}

	
	@Override
	protected  <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Sort sort) {
		return super.getQuery(getMergedSpecification(spec), domainClass, sort);
	}
	
	
}
