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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class InterceptorEntityMapping extends HashMap<String,  List<InterceptorMapping>>  {
	
	private static final long serialVersionUID = 2773330666126105679L;

	private class Comparator implements java.util.Comparator<InterceptorMapping>{
		@Override
		public int compare(InterceptorMapping o1, InterceptorMapping o2) {
			return o2.getOrder() - o1.getOrder();
		}
		
	}
	
	public void put(String entityName, InterceptorMapping mapping){
		if (super.get(entityName) == null){
			super.put(entityName, new ArrayList<InterceptorMapping>());
		}	
			super.get(entityName).add(mapping);
			
			Collections.sort(super.get(entityName), new Comparator());
	}
}
