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

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class RepositoryFactoryBean<R extends JpaRepository<T,I>,T,I extends Serializable> extends JpaRepositoryFactoryBean<R, T,I> {

	public RepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

		    return new RepositoryFactory(entityManager);
		  }

		  private static class RepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		    private EntityManager entityManager;

		    public RepositoryFactory(EntityManager entityManager) {
		      super(entityManager);

		      this.entityManager = entityManager;
		    }

		    @SuppressWarnings("unchecked")
			protected Object getTargetRepository(RepositoryMetadata metadata) {

		      return new RepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		    }

		    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

		      // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
		      //to check for QueryDslJpaRepository's which is out of scope.
		      return Repository.class;
		    }
		  }
}
