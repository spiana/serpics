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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;



public class InterceptorMapping implements Ordered{
	@Autowired
	CommerceEngine commerceEngine;
	
	String targetEntity;
	int order = 0;
	Interceptor	interceptor;
	
	

	
	public void setOrder(int order) {
		
		this.order = order;
	}
	
	protected CommerceSessionContext getCurrentContext(){
		return commerceEngine.getCurrentContext();
	}

	public String getTargetEntity() {
		return targetEntity;
	}

	public void setTargetEntity(String targetEntity) {
		this.targetEntity = targetEntity;
	}

	public Interceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	@Override
	public int getOrder() {
		return order;
	}

	
}