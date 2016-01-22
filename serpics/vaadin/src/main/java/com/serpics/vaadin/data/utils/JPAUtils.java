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
