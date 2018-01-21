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

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.commerce.service.AbstractCommerceService;
import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.model.PaymethodlookupPK;
import com.serpics.commerce.data.repositories.PaymentRepository;
import com.serpics.commerce.data.repositories.PaymethodlookupRepository;
import com.serpics.commerce.strategies.PaymentStrategy;

@Service("paymentService")
@Scope("store")
@Transactional
public class PaymentServiceImpl extends AbstractCommerceService implements PaymentService{
	Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Resource
	PaymentRepository paymentRepository;
	
	@Resource
	PaymethodlookupRepository paymethodlookupRepository;
	
	@Override
	
	public Payment createPayment(AbstractOrder order , PaymentIntent intent)  throws PaymentException{
		Assert.notNull(order);
		removePendingPaymentsForOrder(order);

		PaymentStrategy paymenthStrategy = getPaymentStrategy(order);
		if (paymenthStrategy != null)
			return paymenthStrategy.createPayment(order , intent);
		
		return null;

	}

	@Override
	public Payment authorizePayment(Payment payment)  throws PaymentException{
		Assert.notNull(payment);
		PaymentStrategy paymenthStrategy = getPaymentStrategy(payment.getOrder());
		
		if (paymenthStrategy != null)
			payment= paymenthStrategy.authorizePayment(payment);
		
		return payment;
	}

	@Override
	public Payment capturePayment(Payment payment)  throws PaymentException{
		Assert.notNull(payment);
		PaymentStrategy paymenthStrategy = getPaymentStrategy(payment.getOrder());
		
		if (paymenthStrategy != null)
			payment= paymenthStrategy.capturePayment(payment);
		
		return payment;
	}

	@Override
	public Payment voidPayment(Payment payment) throws PaymentException {
		Assert.notNull(payment);
		PaymentStrategy paymenthStrategy = getPaymentStrategy(payment.getOrder());
		
		if (paymenthStrategy != null)
			payment= paymenthStrategy.voidPayment(payment);
		
		return payment;
	}

	@Override
	public Payment refoundPayment(Payment payment)  throws PaymentException{
		Assert.notNull(payment);
		PaymentStrategy paymenthStrategy = getPaymentStrategy(payment.getOrder());
		
		if (paymenthStrategy != null)
			payment= paymenthStrategy.refoundPayment(payment);
		
		return payment;
		
	}

	private PaymentStrategy getPaymentStrategy(AbstractOrder order )  throws PaymentException{
		Assert.notNull(order);
		Paymethod pmethod = order.getPaymethod();
		
		if (pmethod != null && pmethod.getPaymentStrategy() != null){
			PaymentStrategy paymentStrategy = (PaymentStrategy) getEngine().getApplicationContext().getBean(pmethod.getPaymentStrategy());
			if (paymentStrategy != null)
				return paymentStrategy;
			else
				throw new PaymentException(String.format("Payment stategy %s not found for order %d",
						pmethod != null ? pmethod.getPaymentStrategy() : null , order.getId()));
		}else {
			throw new PaymentException(String.format("Payment method %s not found or payment strategy not set, for order %d ",
					pmethod != null ? pmethod.getName() : null , order.getId()));
		}
	
	}

	@Override
	public Payment findCurrentPendingPayment(final AbstractOrder order) {
		List<Payment> payments = findPendingPaymentsForOrder(order);
		
		if (payments.size() > 0)
			return payments.get(0);
		
		return null;
	}
	
	public List<Payment> findPendingPaymentsForOrder(final AbstractOrder order) {
		List<Payment> payments = paymentRepository.findAll(new Specification<Payment>() {
			
			@Override
			public Predicate toPredicate(Root<Payment> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				
				Predicate p = cb.equal(root.get("order"), order);
				Predicate p1 = cb.equal(root.get("state"),PaymentState.CREATED);
				return cb.and(p,p1);
			}
		});
		
		return payments;
	}
	
	public void removePendingPaymentsForOrder(AbstractOrder order){
		List<Payment> payments = findPendingPaymentsForOrder(order);
		
		for (Payment payment : payments) {
			payment.setState(PaymentState.CANCELLED);
			paymentRepository.saveAndFlush(payment);
		}
		
	}

	@Override
	public Paymethodlookup findPaymethodInfo(Paymethod paymethod) {
		PaymethodlookupPK k = new PaymethodlookupPK();
		k.setPaymethodId(paymethod.getPaymethodId());
		k.setStoreId(getCurrentContext().getStoreId());
		
		return paymethodlookupRepository.findOne(k);
	}
}
