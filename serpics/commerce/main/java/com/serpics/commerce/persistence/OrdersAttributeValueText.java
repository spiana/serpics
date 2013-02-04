package com.serpics.commerce.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the orders_attribute_value_text database table.
 * 
 */
@Entity
@Table(name = "orders_attribute_value_text")
public class OrdersAttributeValueText extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	@Lob()
	@Column(nullable = false)
	private String value;

	// bi-directional one-to-one association to OrdersAttribute
	@OneToOne
	@JoinColumn(name = "attribute_id", nullable = false, insertable = false, updatable = false)
	private OrdersAttribute ordersAttribute;

	public OrdersAttributeValueText() {
	}

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public OrdersAttribute getOrdersAttribute() {
		return this.ordersAttribute;
	}

	public void setOrdersAttribute(OrdersAttribute ordersAttribute) {
		this.ordersAttribute = ordersAttribute;
	}

}