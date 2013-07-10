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
 * The persistent class for the orderitems_attribute database table.
 * 
 */
@Entity
@Table(name = "orderitems_attribute")
public class OrderitemsAttribute extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	@Column(name = "base_attributes_id", nullable = false)
	private BigInteger baseAttributesId;

	private double sequence;

	// bi-directional many-to-one association to Orderitem
	@ManyToOne
	@JoinColumn(name = "orderitems_id", nullable = false)
	private Orderitem orderitem;

	// bi-directional one-to-one association to OrderitemsAttributeValueDatetime
	@OneToOne(mappedBy = "orderitemsAttribute")
	private OrderitemsAttributeValueDatetime orderitemsAttributeValueDatetime;

	// bi-directional one-to-one association to OrderitemsAttributeValueDecimal
	@OneToOne(mappedBy = "orderitemsAttribute")
	private OrderitemsAttributeValueDecimal orderitemsAttributeValueDecimal;

	// bi-directional one-to-one association to OrderitemsAttributeValueLong
	@OneToOne(mappedBy = "orderitemsAttribute")
	private OrderitemsAttributeValueLong orderitemsAttributeValueLong;

	// bi-directional one-to-one association to OrderitemsAttributeValueText
	@OneToOne(mappedBy = "orderitemsAttribute")
	private OrderitemsAttributeValueText orderitemsAttributeValueText;

	// bi-directional one-to-one association to OrderitemsAttributeValueVarchar
	@OneToOne(mappedBy = "orderitemsAttribute")
	private OrderitemsAttributeValueVarchar orderitemsAttributeValueVarchar;

	public OrderitemsAttribute() {
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

	public Orderitem getOrderitem() {
		return this.orderitem;
	}

	public void setOrderitem(Orderitem orderitem) {
		this.orderitem = orderitem;
	}

	public OrderitemsAttributeValueDatetime getOrderitemsAttributeValueDatetime() {
		return this.orderitemsAttributeValueDatetime;
	}

	public void setOrderitemsAttributeValueDatetime(OrderitemsAttributeValueDatetime orderitemsAttributeValueDatetime) {
		this.orderitemsAttributeValueDatetime = orderitemsAttributeValueDatetime;
	}

	public OrderitemsAttributeValueDecimal getOrderitemsAttributeValueDecimal() {
		return this.orderitemsAttributeValueDecimal;
	}

	public void setOrderitemsAttributeValueDecimal(OrderitemsAttributeValueDecimal orderitemsAttributeValueDecimal) {
		this.orderitemsAttributeValueDecimal = orderitemsAttributeValueDecimal;
	}

	public OrderitemsAttributeValueLong getOrderitemsAttributeValueLong() {
		return this.orderitemsAttributeValueLong;
	}

	public void setOrderitemsAttributeValueLong(OrderitemsAttributeValueLong orderitemsAttributeValueLong) {
		this.orderitemsAttributeValueLong = orderitemsAttributeValueLong;
	}

	public OrderitemsAttributeValueText getOrderitemsAttributeValueText() {
		return this.orderitemsAttributeValueText;
	}

	public void setOrderitemsAttributeValueText(OrderitemsAttributeValueText orderitemsAttributeValueText) {
		this.orderitemsAttributeValueText = orderitemsAttributeValueText;
	}

	public OrderitemsAttributeValueVarchar getOrderitemsAttributeValueVarchar() {
		return this.orderitemsAttributeValueVarchar;
	}

	public void setOrderitemsAttributeValueVarchar(OrderitemsAttributeValueVarchar orderitemsAttributeValueVarchar) {
		this.orderitemsAttributeValueVarchar = orderitemsAttributeValueVarchar;
	}

}