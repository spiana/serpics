package com.serpics.core.data;

import java.util.List;

import com.serpics.core.EngineFactory;

public class InterceptorMapping<Z> {

	InterceptorMappingInitializer interceptorMappingInitializer;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Z performBeforeCreateInterceptor(Z entity) {
		checkInitialize();
		InterceptorEntityMapping<BeforeCreateInterceptor> beforeCreate = interceptorMappingInitializer
				.getBeforeCreateinterceptor();
		List<BeforeCreateInterceptor> interceptors = beforeCreate.get(entity
				.getClass().getSimpleName());
		if (interceptors != null) {
			for (BeforeCreateInterceptor beforeSaveInterceptor : interceptors) {
				beforeSaveInterceptor.beforeCreate(entity);
			}
		}
		return entity;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Z performAfterCreateInterceptor(Z entity) {
		checkInitialize();
		InterceptorEntityMapping<AfterCreateInterceptor> afterCreate = interceptorMappingInitializer
				.getAfterCreateinterceptor();
		List<AfterCreateInterceptor> interceptors = afterCreate.get(entity
				.getClass().getSimpleName());
		if (interceptors != null) {
			for (AfterCreateInterceptor beforeSaveInterceptor : interceptors) {
				beforeSaveInterceptor.afterCreate(entity);
			}
		}
		return entity;
	}

	private void checkInitialize() {
		if (interceptorMappingInitializer == null) {
			interceptorMappingInitializer = EngineFactory
					.getCurrentApplicationContext().getBean(
							InterceptorMappingInitializer.class);
		}
	}
}
