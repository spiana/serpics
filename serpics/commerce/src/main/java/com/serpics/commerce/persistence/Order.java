package com.serpics.commerce.persistence;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Order")
@DiscriminatorValue(value = "0")
public class Order extends AbstractOrder {

	public Order() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "orderNumber", unique = true, nullable = true, length = 250)
	protected String orderNumber;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date created;

	@Column(name = "pay_amount", nullable = true, precision = 10, scale = 4)
	private BigDecimal payAmount = new BigDecimal(0);

	// bi-directional many-to-one association to Orderpayment
	@OneToMany(mappedBy = "order")
	private Set<Orderpayment> orderpayments = new HashSet<Orderpayment>(0);

	public Set<Orderpayment> getOrderpayments() {
		return orderpayments;
	}

	public void setOrderpayments(Set<Orderpayment> orderpayments) {
		this.orderpayments = orderpayments;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	@PrePersist
	public void prepareNewOrder() {
		if (this.status == null) {
			this.status = AbstractOrder.PENDING;
		}
		// if (this.orderNumber == null)
		// orderNumber = "1";

		this.created = new Date();

	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
