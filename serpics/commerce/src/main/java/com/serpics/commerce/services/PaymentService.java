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
package com.serpics.commerce.services;

import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;

public interface PaymentService {

	public Payment createPayment(AbstractOrder order , PaymentIntent intent) throws PaymentException;
	public Payment authorizePayment(Payment	 payment)  throws PaymentException;
	public Payment capturePayment(Payment payment)  throws PaymentException;
	public Payment voidPayment(Payment payment) throws PaymentException;
	public Payment refoundPayment(Payment payment)  throws PaymentException;
	public Payment findCurrentPendingPayment(AbstractOrder order)  throws PaymentException;
	public Paymethodlookup findPaymethodInfo(Paymethod paymethod);
	
}
