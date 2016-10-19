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
package com.serpics.core.facade;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

public abstract class AbstractConverter<SOURCE , TARGET> implements Converter<SOURCE, TARGET> , Populator<SOURCE, TARGET> {

	Class<TARGET> targetClass;
	
	@Override
	public TARGET convert(SOURCE source){
		 TARGET target;
		 target = createTarget();
		 populate(source , target);
		 
		 return target;
	}
	


	public TARGET createTarget() {
			Assert.notNull(targetClass);
			try
			{
				return targetClass.newInstance();
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
	}



	@Required
	public void setTargetClass(Class<TARGET> targetClass) {
		this.targetClass = targetClass;
	}
}
