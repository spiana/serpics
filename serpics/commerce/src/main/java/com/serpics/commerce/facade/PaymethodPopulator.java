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
package com.serpics.commerce.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.facade.data.PaymethodData;
import com.serpics.core.facade.Populator;

public class PaymethodPopulator implements Populator<Paymethod, PaymethodData>{
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override 
	public void populate(Paymethod source, PaymethodData target) {		
	
		target.setPaymethodId(source.getPaymethodId());
		target.setCreated(source.getCreated());
		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		target.setName(source.getName());
		
		target.setUuid(source.getUuid());
		target.setUpdated(source.getUpdated());
		
	}

	
}
