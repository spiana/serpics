package com.serpics.core.data;

import org.springframework.beans.factory.annotation.Required;



public interface Interceptor {

	@Required
	public void setEntityName( String entityName);
	public String getEntityName();
	
	@Required
	public void setOrder(Integer Order);
	public Integer getOrder();
	
}
