package com.serpics.admin;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.FactoryBean;

public class SerpicsEntityProviderFactory {

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public SerpicsEntityProviderFactory(Class<?> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	private Class<?> entityClass;


	public SerpicsEntityProvider getObject() throws Exception {
		SerpicsEntityProvider provider = new SerpicsEntityProvider(entityClass);
		provider.setEmf(emf);
		return provider;
	}


	public Class getObjectType() {
		return entityClass;
	}


	public boolean isSingleton() {
		return false;
	}
	
	
}
