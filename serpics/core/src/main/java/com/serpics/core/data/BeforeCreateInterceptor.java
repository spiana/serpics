package com.serpics.core.data;

import com.serpics.core.persistence.jpa.AbstractEntity;

public interface BeforeCreateInterceptor<T> extends Interceptor {
	
	public void beforeCreate(T entity);

}
