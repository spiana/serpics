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
package com.serpics.vaadin.data.utils;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JPAUtils implements ApplicationContextAware{
	
	private static JPAUtils instance;
	
	@PersistenceContext
	EntityManager em ;
	
	
	
	public Set<EntityType<?>> retriveEntityTypes(){
		Set<EntityType<?>> l = em.getMetamodel().getEntities();
		return l;
	}


	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		instance = arg0.getBean(JPAUtils.class);
		
	}

	public static JPAUtils get(){
		return instance;
	}
	
}
