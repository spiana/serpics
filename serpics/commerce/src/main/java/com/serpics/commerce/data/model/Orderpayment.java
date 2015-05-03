package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.base.data.model.Paymethod;

/**
 * The persistent class for the orderpayment database table.
 * 
 */
@Entity
@Table(name = "orderpayment")
public class Orderpayment extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderpayment_id", unique = true, nullable = false)
    private Long orderpaymentId;

    @Column(precision = 10, scale = 4)
    private Double amount;

    // bi-directional many-to-one association to PaymentMethod
    @ManyToOne
    @JoinColumn(name = "paymethod_id", nullable = false)
    private Paymethod paymethod;

    // bi-directional many-to-one association to Order
    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    @XmlTransient
    private Order order;

    public Orderpayment() {
    }

    public Orderpayment(final Paymethod paymethod, final Double amount) {
        this.paymethod = paymethod;
        setAmount(amount);
    }

    public Long getOrderpaymentId() {
        return this.orderpaymentId;
    }

    public void setOrderpaymentId(final Long orderpaymentId) {
        this.orderpaymentId = orderpaymentId;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public Paymethod getPaymethod() {
        return this.paymethod;
    }

    public void setPaymethod(final Paymethod paymethod) {
        this.paymethod = paymethod;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

}