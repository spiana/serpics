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
package com.serpics.commerce.data.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.core.data.jpa.AbstractEntity;
import com.serpics.i18n.data.model.Currency;

@Entity
public class Payment extends AbstractEntity{
	private static final long serialVersionUID = 2556367988611741824L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull(message ="{payment.intent.notnull}")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentIntent intent;
	
	@NotNull(message ="{payment.paymentIdentifier.notnull}")
	@Column(nullable=false)
	private String paymentIdentifier;

	@NotNull(message ="{payment.amount.notnull}")
	@Column(nullable=false)
	private Double amount;
	
	private Double authorizedAmount;
	private Double captureAmount;
	private Double refoundAmount;
	
	private String PayerId;
	
	
	@ManyToOne
	@NotNull(message ="{payment.currency.notnull}")
	@JoinColumn(name="currence_id" , nullable=false)
	private Currency currency;
	
	@NotNull(message ="{payment.state.notnull}")
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private PaymentState state; 
	
	private String payment_method;
	
	private String authorizedURL ;
	
	
	@ManyToOne
	@JoinColumn(name="order_id" , nullable=false)
	@NotNull(message ="{payment.order.notnull}")
	private AbstractOrder order;
	
	@ManyToOne
	@JoinColumn(name="paymethod_id" , nullable=false)
	@NotNull(message ="{payment.paymethod.notnull}")
	private Paymethod paymethod;

	
	@OneToMany(mappedBy="payment", fetch=FetchType.LAZY , cascade=CascadeType.ALL , orphanRemoval=true)
	Set<PaymentTransaction> transactions;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAuthorizedAmount() {
		return authorizedAmount;
	}

	public void setAuthorizedAmount(Double authorizedAmount) {
		this.authorizedAmount = authorizedAmount;
	}

	public Double getCaptureAmount() {
		return captureAmount;
	}

	public void setCaptureAmount(Double captureAmount) {
		this.captureAmount = captureAmount;
	}

	public Double getRefoundAmount() {
		return refoundAmount;
	}

	public void setRefoundAmount(Double refoundAmount) {
		this.refoundAmount = refoundAmount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public AbstractOrder getOrder() {
		return order;
	}

	public void setOrder(AbstractOrder order) {
		this.order = order;
	}

	public Paymethod getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}

	public String getPaymentIdentifier() {
		return paymentIdentifier;
	}

	public void setPaymentIdentifier(String paymentIdentifier) {
		this.paymentIdentifier = paymentIdentifier;
	}

	public Set<PaymentTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<PaymentTransaction> transactions) {
		this.transactions = transactions;
	}

	public PaymentIntent getIntent() {
		return intent;
	}

	public void setIntent(PaymentIntent intent) {
		this.intent = intent;
	}

	public PaymentState getState() {
		return state;
	}

	public void setState(PaymentState state) {
		this.state = state;
	}

	public String getAuthorizedURL() {
		return authorizedURL;
	}

	public void setAuthorizedURL(String authorizedURL) {
		this.authorizedURL = authorizedURL;
	}

	public String getPayerId() {
		return PayerId;
	}

	public void setPayerId(String payerId) {
		PayerId = payerId;
	}

	
	
}
