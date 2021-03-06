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



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.serpics.commerce.PaymentTransactionState;
import com.serpics.commerce.PaymentTransactionType;
import com.serpics.core.data.jpa.AbstractEntity;
@Entity(name="paymentTransaction")
public class PaymentTransaction extends AbstractEntity {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8060844144474643891L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "paytransaction_id", unique = true, nullable = false)
	 private Long id ;
	 
	 @Column(name="transaction_id" , nullable=false)
	 @NotNull(message ="{paymentTransaction.transactionId.notnull}")
	 private String transactionId;

	 @Column(nullable=false )
	 @NotNull(message ="{paymentTransaction.amount.notnull}")
	 private Double amount;
	 
	 @Enumerated(EnumType.STRING)
	 private PaymentTransactionType transactionType;
	 
	 @Enumerated(EnumType.STRING)
	 private PaymentTransactionState state ; 
	 
	 private String errorMessage;
	 
	 @Temporal(TemporalType.DATE)
	 private Date validUntil;

	 @ManyToOne
	 Payment payment;

	@OneToOne
	@JoinColumn(name="referred_transaction_id", insertable=true, updatable=false)
	PaymentTransaction transaction;
	
	private String reason_code;
	 
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


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public PaymentTransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(PaymentTransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public PaymentTransactionState getState() {
		return state;
	}

	public void setState(PaymentTransactionState state) {
		this.state = state;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public PaymentTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(PaymentTransaction transaction) {
		this.transaction = transaction;
	}

	public String getReason_code() {
		return reason_code;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}
	 
}
