package com.serpics.core.data;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.core.persistence.jpa.AbstractEntity;



public abstract class AbstractInterceptor implements Interceptor{

	String entityName;
	Integer order;
	
	@Override
	public String getEntityName() {
		return entityName;
	}
	@Override
	@Required
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@Override
	@Required
	public Integer getOrder() {
		return order;
	}
	
	@Override
	public void setOrder(Integer order) {
		this.order = order;
	}
}
