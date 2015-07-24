package com.serpics.core.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;



public class InterceptorMapping implements Ordered{
	@Autowired
	CommerceEngine commerceEngine;
	
	String targetEntity;
	int order = Integer.MIN_VALUE;
	Interceptor	interceptor;
	
	

	
	public void setOrder(int order) {
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

	@Override
	public int getOrder() {
		return order;
	}

	
	
}
