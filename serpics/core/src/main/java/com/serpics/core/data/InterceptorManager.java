package com.serpics.core.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.core.EngineFactory;

public class InterceptorManager<Z> {
	private static transient Logger LOG = LoggerFactory.getLogger(InterceptorManager.class);
	
	InterceptorMappingInitializer interceptorMappingInitializer;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Z performBeforeSaveInterceptor(final Z entity) {
		checkInitialize();
		
		final InterceptorEntityMapping beforeCreate = interceptorMappingInitializer
				.getCreateInterceptor();
		
		executeBeforeSave(entity, entity.getClass(), beforeCreate);
		
		return entity;

	}
	
	public Z performAfterSaveInterceptor(final Z entity) {
		checkInitialize();
		final InterceptorEntityMapping afterCreate = interceptorMappingInitializer
				.getCreateInterceptor();
		
		executeAfterSave(entity, entity.getClass(), afterCreate);
	
		return entity;
	}


	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void executeBeforeSave(final Z entity , Class<?> clazz  ,InterceptorEntityMapping mapping ){
		
		if (clazz.getSuperclass() != null && !clazz.getSuperclass().isInstance(Object.class))
			executeBeforeSave(entity, clazz.getSuperclass(), mapping);
		
		final List<InterceptorMapping> interceptorMapping = mapping.get(clazz.getName());
		
		if (interceptorMapping != null) {
			for (InterceptorMapping beforeSaveInterceptor : interceptorMapping) {
				if(LOG.isDebugEnabled())
					LOG.debug("perform before create interceptor {} for entity {}" ,
							beforeSaveInterceptor.getInterceptor().getClass().getName(),
							clazz.getName());
				
				((SaveInterceptor) beforeSaveInterceptor.getInterceptor()).beforeSave(entity);
			}
		}else{
			if(LOG.isDebugEnabled())
				LOG.debug("interceptorMapping for entity {} non found in context !" ,clazz.getName()); 
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void executeAfterSave(final Z entity , Class<?> clazz  ,InterceptorEntityMapping mapping ){
		
		if (clazz.getSuperclass() != null && !clazz.getSuperclass().isInstance(Object.class))
			executeAfterSave(entity, clazz.getSuperclass(), mapping);
		
		final List<InterceptorMapping> interceptorMapping = mapping.get(clazz.getName());
		
		if (interceptorMapping != null) {
			for (InterceptorMapping beforeSaveInterceptor : interceptorMapping) {
				if(LOG.isDebugEnabled())
					LOG.debug("perform before create interceptor {} for entity {}" ,
							beforeSaveInterceptor.getInterceptor().getClass().getName(),
							clazz.getName());
				((SaveInterceptor) beforeSaveInterceptor.getInterceptor()).afterSave(entity);
			}
		}else{
			if(LOG.isDebugEnabled())
				LOG.debug("interceptorMapping for entity {} non found in context !" ,clazz.getName()); 
		}
		
	}

	private void checkInitialize() {
		if (interceptorMappingInitializer == null) {
			interceptorMappingInitializer = EngineFactory
					.getCurrentApplicationContext().getBean(
							InterceptorMappingInitializer.class);
		}
	}
	
}
