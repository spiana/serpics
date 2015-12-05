package com.serpics.commerce.data.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.MultiValueAttribute;

/**
 * The persistent class for the orderitems_attribute database table.
 * 
 */
@Entity
@Table(name = "orderitems_attribute")
public class OrderitemsAttribute extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "attribute_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "base_attributes_id", nullable = false)
    private BigInteger baseAttributesId;

    private double sequence;

    // bi-directional many-to-one association to Orderitem
    @ManyToOne
    @JoinColumn(name = "orderitems_id", nullable = false)
    private AbstractOrderitem orderitem;

    @Embedded
    private MultiValueAttribute value;
   
    public OrderitemsAttribute() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long attributeId) {
        this.id = attributeId;
    }

    public BigInteger getBaseAttributesId() {
        return this.baseAttributesId;
    }

    public void setBaseAttributesId(final BigInteger baseAttributesId) {
        this.baseAttributesId = baseAttributesId;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public AbstractOrderitem getOrderitem() {
        return this.orderitem;
    }

    public void setOrderitem(final AbstractOrderitem orderitem) {
        this.orderitem = orderitem;
    }

	public MultiValueAttribute getValue() {
		return value;
	}

	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}

   

}