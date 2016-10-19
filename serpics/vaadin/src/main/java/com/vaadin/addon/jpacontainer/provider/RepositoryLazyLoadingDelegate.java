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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;

public class RepositoryLazyLoadingDelegate implements LazyLoadingDelegate {
	private static transient Logger logger = LoggerFactory.getLogger(RepositoryLazyLoadingDelegate.class);


	EntityProvider entityProvider;
	
	@Override
	public <E> E ensureLazyPropertyLoaded(E entity, String property) {
	
		entityProvider.refreshEntity(entity);
		
		final String getter = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
		
		if (logger.isDebugEnabled())
			logger.debug("try to execute getter {}" , getter);
		
		try{
			final Method m =entity.getClass().getMethod(getter, (Class<?>[]) null);
			if (logger.isDebugEnabled())
				logger.debug("Invoke method [{}] , with result [{}]", m.getName(),
						m.invoke(entity.getClass(), (Object[]) null));

			m.invoke(entity, (Object[]) null);
		}catch(NoSuchMethodException e){
			 throw new RuntimeException("Could not set lazy loaded value for entity.", e);
		}catch (InvocationTargetException e) {
			 throw new RuntimeException("Could not set lazy loaded value for entity.", e);
		}catch (IllegalAccessException e){
			 throw new RuntimeException("Could not set lazy loaded value for entity.", e);
			
		}
          
		return entity;
	}
	

	private Object recurseIfNested(String propertyName, Object value)
	{
	  if (isNestedProperty(propertyName))
	  {
	    Object subEntity = ((List)value).get(0);
	    String subProperty = propertyName.substring(propertyName.indexOf(46) + 1);

	    value = ensureLazyPropertyLoaded(subEntity, subProperty);
	  }
	  return value;
	}
	private boolean isNestedProperty(String propertyName)
	{
	  return (propertyName.indexOf(46) != -1);
	}

	@Override
	public void setEntityProvider(EntityProvider<?> entityProvider) {
		this.entityProvider = entityProvider;
	}

}
