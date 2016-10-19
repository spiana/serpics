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
package com.vaadin.addon.jpacontainer.provider;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.core.EngineFactory;
import com.serpics.core.data.RepositoryInitializer;
import com.vaadin.addon.jpacontainer.util.DefaultQueryModifierDelegate;

public class CustomQueryModifierDelegate extends DefaultQueryModifierDelegate {

	private Class<?> mappedClass;

	private static RepositoryInitializer initializer;

	@Override
	public void filtersWillBeAdded(CriteriaBuilder criteriaBuilder,
			CriteriaQuery<?> query, List<Predicate> predicates) {
		if (query != null) {
			List<Specification> specifications = getInitializer().getSpecificationForClass(mappedClass);
			
			if (specifications != null) {
				Root root = query.getRoots().iterator().next();
				for (Specification specification : specifications) {
					Predicate p = specification.toPredicate(root, query, criteriaBuilder);
					if( p != null)
						predicates.add(p);
				}
				
			}
		}
		
		super.filtersWillBeAdded(criteriaBuilder, query, predicates);
	}

	public RepositoryInitializer getInitializer() {
		if (initializer == null)
			initializer = EngineFactory.getCurrentApplicationContext().getBean(
					RepositoryInitializer.class);

		return initializer;
	}

	public void setClassMetadata(Class<?> clazz) {
		this.mappedClass = clazz;
	}
	
}
