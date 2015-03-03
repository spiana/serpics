package com.serpics.core.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.persistence.jpa.AbstractEntity;



public class InterceptorMapping{
	@Autowired
	CommerceEngine commerceEngine;
	
	String targetEntity;
	Integer order;
	Interceptor	interceptor;
	
	
	
	public Integer getOrder() {
		return order;
	}
	
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	protected CommerceSessionContext getCurrentContext(){
		return commerceEngine.getCurrentContext();
	}

	public String getTargetEntity() {
		return targetEntity;
	}

	public void setTargetEntity(String targetEntity) {
		this.targetEntity = targetEntity;
	}

	public Interceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	
}
