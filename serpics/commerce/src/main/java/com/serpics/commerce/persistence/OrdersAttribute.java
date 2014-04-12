package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the orders_attribute database table.
 * 
 */
@Entity
@Table(name = "orders_attribute")
public class OrdersAttribute extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	@Column(name = "base_attributes_id", nullable = false)
	private BigInteger baseAttributesId;

	private double sequence;

	// bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name = "orders_id", nullable = false)
	private AbstractOrder order;

	// bi-directional one-to-one association to OrdersAttributeValueDatetime
	@OneToOne(mappedBy = "ordersAttribute")
	private OrdersAttributeValueDatetime ordersAttributeValueDatetime;

	// bi-directional one-to-one association to OrdersAttributeValueDecimal
	@OneToOne(mappedBy = "ordersAttribute")
	private OrdersAttributeValueDecimal ordersAttributeValueDecimal;

	// bi-directional one-to-one association to OrdersAttributeValueLong
	@OneToOne(mappedBy = "ordersAttribute")
	private OrdersAttributeValueLong ordersAttributeValueLong;

	// bi-directional one-to-one association to OrdersAttributeValueText
	@OneToOne(mappedBy = "ordersAttribute")
	private OrdersAttributeValueText ordersAttributeValueText;

	// bi-directional one-to-one association to OrdersAttributeValueVarchar
	@OneToOne(mappedBy = "ordersAttribute")
	private OrdersAttributeValueVarchar ordersAttributeValueVarchar;

	public OrdersAttribute() {
	}

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public BigInteger getBaseAttributesId() {
		return this.baseAttributesId;
	}

	public void setBaseAttributesId(BigInteger baseAttributesId) {
		this.baseAttributesId = baseAttributesId;
	}

	public double getSequence() {
		return this.sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public AbstractOrder getOrder() {
		return this.order;
	}

	public void setOrder(AbstractOrder order) {
		this.order = order;
	}

	public OrdersAttributeValueDatetime getOrdersAttributeValueDatetime() {
		return this.ordersAttributeValueDatetime;
	}

	public void setOrdersAttributeValueDatetime(OrdersAttributeValueDatetime ordersAttributeValueDatetime) {
		this.ordersAttributeValueDatetime = ordersAttributeValueDatetime;
	}

	public OrdersAttributeValueDecimal getOrdersAttributeValueDecimal() {
		return this.ordersAttributeValueDecimal;
	}

	public void setOrdersAttributeValueDecimal(OrdersAttributeValueDecimal ordersAttributeValueDecimal) {
		this.ordersAttributeValueDecimal = ordersAttributeValueDecimal;
	}

	public OrdersAttributeValueLong getOrdersAttributeValueLong() {
		return this.ordersAttributeValueLong;
	}

	public void setOrdersAttributeValueLong(OrdersAttributeValueLong ordersAttributeValueLong) {
		this.ordersAttributeValueLong = ordersAttributeValueLong;
	}

	public OrdersAttributeValueText getOrdersAttributeValueText() {
		return this.ordersAttributeValueText;
	}

	public void setOrdersAttributeValueText(OrdersAttributeValueText ordersAttributeValueText) {
		this.ordersAttributeValueText = ordersAttributeValueText;
	}

	public OrdersAttributeValueVarchar getOrdersAttributeValueVarchar() {
		return this.ordersAttributeValueVarchar;
	}

	public void setOrdersAttributeValueVarchar(OrdersAttributeValueVarchar ordersAttributeValueVarchar) {
		this.ordersAttributeValueVarchar = ordersAttributeValueVarchar;
	}

}