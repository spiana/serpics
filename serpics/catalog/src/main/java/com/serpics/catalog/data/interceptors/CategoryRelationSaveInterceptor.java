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

import org.springframework.util.Assert;

import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.SaveInterceptor;

public class CategoryRelationSaveInterceptor implements SaveInterceptor<CategoryRelation> {

	@Override
	public void beforeSave(CategoryRelation entity) {
	    if (entity.getId() == null){
	    	Assert.notNull(entity.getParentCategory());
	    	Assert.notNull(entity.getChildCategory());
	    	
            final CtentryRelationPK pk = new CtentryRelationPK(entity.getParentCategory().getId(), entity
                    .getChildCategory().getId());
            entity.setId(pk);
        }
	}

	@Override
	public void afterSave(CategoryRelation entity) {
		// TODO Auto-generated method stub
		
	}

}
