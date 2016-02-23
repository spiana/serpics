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

import com.serpics.base.data.model.Currency;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.core.data.jpa.AbstractEntity;

@Entity
public class Payment extends AbstractEntity{
	private static final long serialVersionUID = 2556367988611741824L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id", unique = true, nullable = false)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentIntent intent;
	
	@Column(nullable=false)
	private String paymentIdentifier;

	@Column(nullable=false)
	private Double amount;
	
	private Double authorizedAmount;
	private Double captureAmount;
	private Double refoundAmount;
	
	private String PayerId;
	
	
	@ManyToOne
	@JoinColumn(name="currence_id" , nullable=false)
	private Currency currency;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private PaymentState state; 
	
	private String payment_method;
	
	private String authorizedURL ;
	
	
	@ManyToOne
	@JoinColumn(name="order_id" , nullable=false)
	private AbstractOrder order;
	
	@ManyToOne
	@JoinColumn(name="paymethod_id" , nullable=false)
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
