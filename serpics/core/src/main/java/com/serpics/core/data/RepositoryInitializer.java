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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;
import com.serpics.stereotype.DefaultSpec;


@SuppressWarnings("rawtypes")
public class RepositoryInitializer implements InitializingBean , ApplicationContextAware{
	
	private static Logger LOG = LoggerFactory.getLogger(RepositoryInitializer.class);
	
	private static final Map<ClassLoader, RepositoryInitializer> currentContextPerThread = new ConcurrentHashMap<ClassLoader, RepositoryInitializer>(1);
	
	Map<String , List<Specification>> entitySpecifications = new HashMap<String, List<Specification>>();
	Map<String , Repository> entityRepositoryMapping = new HashMap<String, Repository>();
	
	ApplicationContext applicationContext;
	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		Engine<SessionContext> engine = (Engine<SessionContext>)applicationContext.getBean("engine");
		
		Map<String , Object> specs = applicationContext.getBeansWithAnnotation(DefaultSpec.class);
		Set<String> keys = specs.keySet();
		for (String string : keys) {
			Object _m = specs.get(string);	
			Class<?>  c = _m.getClass().getAnnotation(DefaultSpec.class).value();
			
			LOG.info("Found Specification for class {}" , c.getName());
			
			if (_m instanceof  Specification){
				if (!entitySpecifications.containsKey(c.getName()) ){
					List<Specification> l = new ArrayList<Specification>();
					l.add((Specification) _m);
					entitySpecifications.put(c.getName(), l);
				}else{
					List<Specification> l = entitySpecifications.get(c.getName() );
					l.add((Specification) _m);
					//LOG.warn("Specification for entity {} already exist with value {}", c.getName() , _m.getClass().getName() );
				}
			}else{
				LOG.error("Specification found for entity {} is not instance of {}" , c.getClass(), Specification.class.getName());
			}
		}
		
		Map<String, Repository> m =  applicationContext.getBeansOfType(Repository.class);
	
		for (String repository : m.keySet()) {
			Repository i = m.get(repository);
			i.setRepositoryIniziatializer(this);
			entityRepositoryMapping.put(i.getDomainClass().getName() , i);
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader() , this);
	}

	public Map<String, List<Specification>> getEntitySpecifications() {
		return entitySpecifications;
	}
	
	public List<Specification> getSpecificationForClass(Class<?> clazz ){
		Assert.notNull(clazz);
		List<Specification> specs = new ArrayList<Specification>(0);
		recorsiveLoadSpecification(clazz, specs);
		return specs;
		
	}
	
	private void recorsiveLoadSpecification(Class<?> clazz , List<Specification> defaultSepcificationList){

		if (clazz.getSuperclass() != null &&  !clazz.getSuperclass().isAssignableFrom(Object.class))
				recorsiveLoadSpecification(clazz.getSuperclass(), defaultSepcificationList);
			
			List<Specification> specs =entitySpecifications.get(clazz.getName());
			
			if(specs != null){
				defaultSepcificationList.addAll(specs);
				if(LOG.isDebugEnabled()){
					LOG.debug("found specification {} for entity {}" , specs.getClass().getName() , clazz.getName());
				}
			}else {
				if(LOG.isDebugEnabled())	
					LOG.debug("not found specification for entity {}", clazz.getName() );
			}
	}
	
	public Repository getRepositoryForEntity(Class<?> clazz){
			Assert.notNull(clazz);
			Repository repository = entityRepositoryMapping.get(clazz.getName());
			Assert.notNull(repository , String.format("no repository found for class %s" , clazz.getName()));
			return repository;
	}
	
	public static RepositoryInitializer getInstance(){
		return currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
	}
	
}
