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

import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.facade.data.PaymentData;
import com.serpics.core.facade.Populator;

public class PaymentPopulator implements Populator<Payment, PaymentData>{
		
	@Override 
	public void populate(Payment source, PaymentData target) {		
	
		target.setId(source.getId());
		target.setCreated(source.getCreated());

		target.setIntent(source.getIntent().toString());
		
		target.setPaymentIdentifier(source.getPaymentIdentifier());
		target.setAmount(source.getAmount());
		target.setAuthorizedAmount(source.getAuthorizedAmount());
		target.setCaptureAmount(source.getCaptureAmount());
		target.setRefoundAmount(source.getRefoundAmount());
		target.setPayerID(source.getPayerId());
		target.setState(source.getState().toString());
		target.setPayment_method(source.getPayment_method());
		target.setAuthorizedURL(source.getAuthorizedURL());
		
		
		
	}

	
}
