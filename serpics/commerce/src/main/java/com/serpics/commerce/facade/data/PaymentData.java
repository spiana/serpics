package com.serpics.commerce.facade.data;

import com.serpics.core.facade.AbstractData;

public class PaymentData extends AbstractData{

	private String intent;
	private String paymentIdentifier;
	private Double amount;
	private Double authorizedAmount;
	private Double captureAmount;
	private Double refoundAmount;
	private String PayerID;
	private String state;
	private String payment_method;
	private String authorizedURL;
	
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getPaymentIdentifier() {
		return paymentIdentifier;
	}
	public void setPaymentIdentifier(String paymentIdentifier) {
		this.paymentIdentifier = paymentIdentifier;
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
	public String getPayerID() {
		return PayerID;
	}
	public void setPayerID(String PayerID) {
		this.PayerID = PayerID;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getAuthorizedURL() {
		return authorizedURL;
	}
	public void setAuthorizedURL(String authorizedURL) {
		this.authorizedURL = authorizedURL;
	}
	
	

	
}
