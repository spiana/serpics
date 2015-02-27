package com.serpics.core.data;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("rawtypes")
public class InterceptorMappingInitializer implements InitializingBean , ApplicationContextAware{

	ApplicationContext applicationContext;
	

	InterceptorEntityMapping<BeforeCreateInterceptor> beforeCreateinterceptor= new InterceptorEntityMapping<BeforeCreateInterceptor>(); 
	InterceptorEntityMapping<AfterCreateInterceptor> afterCreateinterceptor= new InterceptorEntityMapping<AfterCreateInterceptor>(); 
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
		
		Map<String, BeforeCreateInterceptor> m =  applicationContext.getBeansOfType(BeforeCreateInterceptor.class);
		
		for (String interceptor : m.keySet()) {
			BeforeCreateInterceptor i = m.get(interceptor);
			this.beforeCreateinterceptor.put(i.getEntityName() ,  i);
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}


	public InterceptorEntityMapping<BeforeCreateInterceptor> getBeforeCreateinterceptor() {
		return beforeCreateinterceptor;
	}


	public InterceptorEntityMapping<AfterCreateInterceptor> getAfterCreateinterceptor() {
		return afterCreateinterceptor;
	}

	
	
}
