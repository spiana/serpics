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
package com.serpics.base.facade;

import org.springframework.beans.BeanUtils;

import com.serpics.base.data.model.TaxCategory;
import com.serpics.base.facade.data.TaxCategoryData;
import com.serpics.core.facade.Populator;

public class TaxCategoryPopulator implements Populator<TaxCategory, TaxCategoryData>{

	@Override
	public void populate(TaxCategory source, TaxCategoryData target) {
			BeanUtils.copyProperties(source, target, "id", "store");
	}

	
}
