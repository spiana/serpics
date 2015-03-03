package com.serpics.core.data;

public interface CreateInterceptor<T> extends Interceptor {
	
	public void beforeCreate(T entity);
	public void afterCreate(T entity);

}
