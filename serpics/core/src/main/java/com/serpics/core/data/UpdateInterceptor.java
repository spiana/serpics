package com.serpics.core.data;

public interface UpdateInterceptor<T>  extends Interceptor{
	
	public void beforeSave(T entity);
	public void afterSave(T entity);
}
