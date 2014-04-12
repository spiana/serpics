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
 * The persistent class for the orderitems_attribute_value_text database table.
 * 
 */
@Entity
@Table(name = "orderitems_attribute_value_text")
public class OrderitemsAttributeValueText extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	@Lob()
	@Column(nullable = false)
	private String value;

	// bi-directional one-to-one association to OrderitemsAttribute
	@OneToOne
	@JoinColumn(name = "attribute_id", nullable = false, insertable = false, updatable = false)
	private OrderitemsAttribute orderitemsAttribute;

	public OrderitemsAttributeValueText() {
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

	public OrderitemsAttribute getOrderitemsAttribute() {
		return this.orderitemsAttribute;
	}

	public void setOrderitemsAttribute(OrderitemsAttribute orderitemsAttribute) {
		this.orderitemsAttribute = orderitemsAttribute;
	}

}