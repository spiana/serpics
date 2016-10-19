/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.core.data;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class InterceptorMappingInitializer implements InitializingBean , ApplicationContextAware{

	private static transient Logger LOG = LoggerFactory.getLogger(InterceptorMappingInitializer.class);
	ApplicationContext applicationContext;
	

	InterceptorEntityMapping createinterceptor= new InterceptorEntityMapping(); 
	InterceptorEntityMapping updateinterceptor= new InterceptorEntityMapping(); 
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		Map<String, InterceptorMapping> m =  applicationContext.getBeansOfType(InterceptorMapping.class);
		
		for (String interceptor : m.keySet()) {
			InterceptorMapping i = m.get(interceptor);
			if (i.getInterceptor() instanceof SaveInterceptor){
				LOG.info(String.format("found save interceptor %s of type %s for entity %s with order %s" , interceptor , 
						i.getInterceptor().getClass().getName(),
						i.getTargetEntity() , i.getOrder()) );
				this.createinterceptor.put(i.getTargetEntity() , i);
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
