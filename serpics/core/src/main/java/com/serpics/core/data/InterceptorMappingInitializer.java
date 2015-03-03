package com.serpics.core.data;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("rawtypes")
public class InterceptorMappingInitializer implements InitializingBean , ApplicationContextAware{

	ApplicationContext applicationContext;
	

	InterceptorEntityMapping createinterceptor= new InterceptorEntityMapping(); 
	InterceptorEntityMapping updateinterceptor= new InterceptorEntityMapping(); 
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		Map<String, InterceptorMapping> m =  applicationContext.getBeansOfType(InterceptorMapping.class);
		
		for (String interceptor : m.keySet()) {
			InterceptorMapping i = m.get(interceptor);
			if (i.getInterceptor() instanceof CreateInterceptor){
				this.createinterceptor.put(i.getTargetEntity() , i);
			}else if(i.getInterceptor() instanceof UpdateInterceptor){
				this.updateinterceptor.put(i.getTargetEntity(), i);
			}else{
				throw new RuntimeException(String.format("invalid interceptor type for entity %s", i.getTargetEntity()));
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}


	public InterceptorEntityMapping getCreateInterceptor() {
		return createinterceptor;
	}


	public InterceptorEntityMapping getUpdateInterceptor() {
		return updateinterceptor;
	}

	
	
}
