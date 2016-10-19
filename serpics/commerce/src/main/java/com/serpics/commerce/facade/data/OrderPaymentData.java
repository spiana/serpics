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
package com.serpics.commerce.facade.data;

import com.serpics.commerce.data.model.Paymethod;
import com.serpics.core.facade.AbstractData;

public class OrderPaymentData extends AbstractData {
	
	private Double amount;

    private Paymethod paymethod;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Paymethod getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}
    
    
}
