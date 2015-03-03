package com.serpics.core.data;

import java.util.List;

import com.serpics.core.EngineFactory;

public class InterceptorManager<Z> {

	InterceptorMappingInitializer interceptorMappingInitializer;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Z performBeforeCreateInterceptor(final Z entity) {
		checkInitialize();
		final InterceptorEntityMapping beforeCreate = interceptorMappingInitializer
				.getCreateInterceptor();

		final List<InterceptorMapping> interceptorMapping = beforeCreate.get(entity
				.getClass().getName());
		if (interceptorMapping != null) {
			for (InterceptorMapping beforeSaveInterceptor : interceptorMapping) {
				((CreateInterceptor) beforeSaveInterceptor.getInterceptor()).beforeCreate(entity);
			}
		}
		return entity;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Z performAfterCreateInterceptor(final Z entity) {
		checkInitialize();
		final InterceptorEntityMapping afterCreate = interceptorMappingInitializer
				.getCreateInterceptor();
		
		final List<InterceptorMapping>  interceptorMapping = afterCreate.get(entity
				.getClass().getName());
		if (interceptorMapping != null) {
			for (InterceptorMapping beforeSaveInterceptor : interceptorMapping) {
				((CreateInterceptor) beforeSaveInterceptor.getInterceptor()).afterCreate(entity);
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
