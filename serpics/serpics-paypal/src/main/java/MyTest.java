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
import java.io.InputStream;
import java.util.ArrayList;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentHistory;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.QueryParameters;



public class MyTest {

	public static void main(String[] args) throws PayPalRESTException {
		
		new MyTest().run();
	}
	
	
	public void run() throws PayPalRESTException{
			InputStream is = MyTest.class.getResourceAsStream("/sdk_config.properties");
		
		Payment.initConfig(is);
			
		String token = new OAuthTokenCredential("AT0OzuuajG2aL0HDVoZro5k3RUTNPEoxhQPpzaHrodClevqM2PCIWfO4NJo7XaEZqcow0YBKcFXAENQC",
				"EBeHkw2Tx6VMk9buswSVDExmLxLGmV80ebt4Mo3OMib7_MPxIhaDPUlr9ZAAXlj08K0xj3bdND8_hpGo").getAccessToken();
	
		QueryParameters queryParameters = new QueryParameters();
		queryParameters.setCount("10");

		PaymentHistory paymentHistory = Payment.list(token, queryParameters.getContainerMap());	
	
		Amount a = new Amount();
		a.setCurrency("EUR");
		a.setTotal("10.00");
		
		Transaction t = new Transaction();
		t.setAmount(a);
		
		RedirectUrls urls= new RedirectUrls();
		urls.setCancelUrl("http://localhost/cancel");
		urls.setReturnUrl("http://localhost/accept");
		
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		Payment p = new Payment();
		p.setIntent("sale");
		p.setPayer(payer);
		p.setRedirectUrls(urls);
	
		java.util.List<Transaction> _l = new ArrayList<Transaction>();
		_l.add(t);
		p.setTransactions(_l);
		
		Payment c = p.create(token);
		System.out.println(c.getState());
		
	}
}
