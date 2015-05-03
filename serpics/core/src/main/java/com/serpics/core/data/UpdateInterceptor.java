package com.serpics.core.data;

import com.serpics.core.data.jpa.AbstractEntity;

public interface UpdateInterceptor<T> extends Interceptor {

	public void beforeUpdate(T entity);
	public void afterUpdate(T entity);
}
