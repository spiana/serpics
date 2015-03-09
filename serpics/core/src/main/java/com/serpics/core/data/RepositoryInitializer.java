package com.serpics.core.data;

import java.util.HashMap;
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

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.stereotype.BaseSpec;


@SuppressWarnings("rawtypes")
public class RepositoryInitializer implements InitializingBean , ApplicationContextAware{
	
	private static Logger LOG = LoggerFactory.getLogger(RepositoryInitializer.class);
	
	private static final Map<ClassLoader, RepositoryInitializer> currentContextPerThread = new ConcurrentHashMap<ClassLoader, RepositoryInitializer>(1);
	
	Map<String , Specification> entitySpecifications = new HashMap<String, Specification>();
	Map<String , Repository> entityRepositoryMapping = new HashMap<String, Repository>();
	
	ApplicationContext applicationContext;
	@Override
	public void afterPropertiesSet() throws Exception {
		CommerceEngine engine = applicationContext.getBean(CommerceEngine.class);
		
		Map<String , Object> specs = applicationContext.getBeansWithAnnotation(BaseSpec.class);
		Set<String> keys = specs.keySet();
		for (String string : keys) {
			Object _m = specs.get(string);	
			Class<?>  c = _m.getClass().getAnnotation(BaseSpec.class).value();
			if(LOG.isDebugEnabled())
				LOG.debug("Found Specification for class {}" , c.getName());
			if (_m instanceof  Specification){
				if (!entitySpecifications.containsKey(c.getName())){
					entitySpecifications.put(c.getName(), (Specification) _m);
				}else{
					LOG.warn("Specification for entity {} already vith value {}!", c.getName() , _m.getClass().getName() );
				}
			}else{
				LOG.error("Specification found for wntity {} is not instance of {}" , c.getClass(), Specification.class.getName());
			}
		}
		
		Map<String, Repository> m =  applicationContext.getBeansOfType(Repository.class);
	
		for (String repository : m.keySet()) {
			Repository i = m.get(repository);
			i.setEngine(engine);
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

	public Map<String, Specification> getEntitySpecifications() {
		return entitySpecifications;
	}
	
	public Specification getSpecificationForClass(Class<?> clazz){
		Assert.notNull(clazz);
		Specification spec =entitySpecifications.get(clazz.getName());
		if(spec != null){
			LOG.info("found specification {} for entity {}" , spec.getClass().getName() , clazz.getName());
		}else{
			LOG.info("not found specification for entity {}", clazz.getName() );
		}
		return spec;
		
	}
	
	public Repository getRepositoryForEntity(Class<?> clazz){
			Assert.notNull(clazz);
			return entityRepositoryMapping.get(clazz.getName());
	}
	
	public static RepositoryInitializer getInstance(){
		return currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
	}
	
}
