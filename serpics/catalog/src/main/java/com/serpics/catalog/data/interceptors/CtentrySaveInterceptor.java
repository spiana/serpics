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
package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.Ctentry;
import com.serpics.core.data.SaveInterceptor;

public class CtentrySaveInterceptor implements SaveInterceptor<Ctentry>{

	@Override
	public void beforeSave(Ctentry entity) {
//		if (entity.getName() == null){
//			entity.setName(new MultilingualString("en" , "en"));
//		}
//		if (entity.getDescription() == null)
//			entity.setDescription(new MultilingualText("en" , "en"));
	}

	@Override
	public void afterSave(Ctentry entity) {
		//System.out.println(entity);
	}

}
