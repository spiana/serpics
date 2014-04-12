package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the orderpayment database table.
 * 
 */
@Entity
@Table(name = "orderpayment")
public class Orderpayment extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderpayment_id", unique = true, nullable = false)
	private Long orderpaymentId;

	@Column(precision = 10, scale = 4)
	private BigDecimal amount;

	// bi-directional many-to-one association to PaymentMethod
	// @ManyToOne
	// @JoinColumn(name="paymethod_id", nullable=false)
	@Column(name = "paymethod_id", nullable = false)
	private Long paymethod;

	// bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name = "orders_id", nullable = false)
	@XmlTransient
	private Order order;

	public Orderpayment() {
	}

	public Orderpayment(Long paymethod, double amount) {
		this.paymethod = paymethod;
		setAmount(new BigDecimal(amount));
	}

	public Long getOrderpaymentId() {
		return this.orderpaymentId;
	}

	public void setOrderpaymentId(Long orderpaymentId) {
		this.orderpaymentId = orderpaymentId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Long paymethod) {
		this.paymethod = paymethod;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}