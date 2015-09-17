package com.serpics.core.data;

public interface SaveInterceptor<T> extends Interceptor {
	
	public void beforeSave(T entity);
	public void afterSave(T entity);

}
