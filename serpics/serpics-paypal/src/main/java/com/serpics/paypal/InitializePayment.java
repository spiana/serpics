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
package com.serpics.paypal;

import java.io.InputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.paypal.api.payments.Payment;

public class InitializePayment implements InitializingBean{

	private String propertyFile;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream is = InitializePayment.class.getResourceAsStream(propertyFile);
		Assert.notNull(is,"Missing propertyFile for InitializePayment");
		Payment.initConfig(is);
	}

	@Required
	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}

}
