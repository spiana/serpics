package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the orderitems_attribute_value_decimal database
 * table.
 * 
 */
@Entity
@Table(name = "orderitems_attribute_value_decimal")
public class OrderitemsAttributeValueDecimal extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	@Column(nullable = false, precision = 10, scale = 4)
	private BigDecimal value;

	// bi-directional one-to-one association to OrderitemsAttribute
	@OneToOne
	@JoinColumn(name = "attribute_id", nullable = false, insertable = false, updatable = false)
	private OrderitemsAttribute orderitemsAttribute;

	public OrderitemsAttributeValueDecimal() {
	}

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public OrderitemsAttribute getOrderitemsAttribute() {
		return this.orderitemsAttribute;
	}

	public void setOrderitemsAttribute(OrderitemsAttribute orderitemsAttribute) {
		this.orderitemsAttribute = orderitemsAttribute;
	}

}