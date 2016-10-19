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
package com.serpics.initialsetup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Declare which store, catalog and order to run Task.
 *  * 
 * @author alessandro.marasco@tinvention.net
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SystemSetupTaskConfig {
	
	/**
	 * 
	 * @return The realm name of store, Default is default-store
	 */
	String store() default "default-store";
	
	/**
	 * 
	 * @return The name of catalog. Value of default is empty and the system select the default catalog
	 */
	String catalog() default "";
	
	/**
	 * 
	 * @return Order to execute Task
	 */
	int order();
	
	/**
	 * 
	 * @return Small description for log text.
	 */
	String description() default "";
	
}
