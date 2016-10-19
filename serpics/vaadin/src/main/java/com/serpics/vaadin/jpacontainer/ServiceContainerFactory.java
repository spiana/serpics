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
package com.serpics.vaadin.jpacontainer;

import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.Type;

import com.serpics.core.data.RepositoryInitializer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityServiceProvider;

public class ServiceContainerFactory {

	@SuppressWarnings({ "unchecked"})
	public static <T> JPAContainer<T> make(final Class<T> entityClass) {
		final JPAContainer<T> cont = new JPAContainer<T>(entityClass);
		final CachingLocalEntityServiceProvider<T> provider = new CachingLocalEntityServiceProvider<T>(entityClass);
		provider.setCacheEnabled(true);
		cont.setEntityProvider(provider);
		cont.setBuffered(true);
		provider.setRepository(RepositoryInitializer.getInstance().getRepositoryForEntity(entityClass));
		return cont;
	}

	public static Class<?> detectReferencedType(EntityManagerFactory emf,
			Object propertyId, Class masterEntityClass) {
		Class<?> referencedType = null;
		Metamodel metamodel = emf.getMetamodel();
		Set<EntityType<?>> entities = metamodel.getEntities();
		for (EntityType<?> entityType : entities) {
			Class<?> javaType = entityType.getJavaType();
			if (javaType == masterEntityClass) {
				Attribute<?, ?> attribute = entityType.getAttribute(propertyId
						.toString());

				PluralAttribute<?,?,?> pAttribute = (PluralAttribute<?, ?, ?>) attribute;
				Type<?> elementType = pAttribute.getElementType();
				referencedType = elementType.getJavaType();
				break;
			}
		}
		return referencedType;
	}
	

}
