package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the orders_attribute_value_datetime database table.
 * 
 */
@Entity
@Table(name = "orders_attribute_value_datetime")
public class OrdersAttributeValueDatetime extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	private Timestamp value;

	// bi-directional one-to-one association to OrdersAttribute
	@OneToOne
	@JoinColumn(name = "attribute_id", nullable = false, insertable = false, updatable = false)
	private OrdersAttribute ordersAttribute;

	public OrdersAttributeValueDatetime() {
	}

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public Timestamp getValue() {
		return this.value;
	}

	public void setValue(Timestamp value) {
		this.value = value;
	}

	public OrdersAttribute getOrdersAttribute() {
		return this.ordersAttribute;
	}

	public void setOrdersAttribute(OrdersAttribute ordersAttribute) {
		this.ordersAttribute = ordersAttribute;
	}

}