package com.serpics.core.data;

import com.serpics.core.persistence.jpa.AbstractEntity;

public interface AfterCreateInterceptor<T> extends Interceptor {

	public void afterCreate(T entity);
}
