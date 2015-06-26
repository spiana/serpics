package com.serpics.commerce.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the suborders database table.
 * 
 */
@Entity
@Table(name = "suborders")
public class Suborder extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "suborders_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "discount_amount", precision = 10, scale = 4)
    private BigDecimal discountAmount;

    @Column(name = "discount_perc", precision = 10, scale = 4)
    private BigDecimal discountPerc;

    @Column(name = "shipping_address_id", nullable = false)
    private BigInteger shippingAddressId;

    @Column(nullable = false, length = 2)
    private String status;

    @Column(name = "subtotal_amount", nullable = false, precision = 10, scale = 4)
    private BigDecimal subtotalAmount;

    @Column(name = "subtotal_product", nullable = false, precision = 10, scale = 4)
    private BigDecimal subtotalProduct;

    @Column(name = "subtotal_shipping", precision = 10, scale = 4)
    private BigDecimal subtotalShipping;

    @Column(name = "subtotal_tax", precision = 10, scale = 4)
    private BigDecimal subtotalTax;

    // bi-directional many-to-one association to Orderitem
    @OneToMany(mappedBy = "suborder")
    private Set<AbstractOrderitem> orderitems;

    // bi-directional many-to-one association to Order
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private AbstractOrder order;

    // bi-directional many-to-one association to Shipmode
    @ManyToOne
    @JoinColumn(name = "shipmode_id")
    private Shipmode shipmode;

    public Suborder() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long subordersId) {
        this.id = subordersId;
    }

   

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountPerc() {
        return this.discountPerc;
    }

    public void setDiscountPerc(final BigDecimal discountPerc) {
        this.discountPerc = discountPerc;
    }

    public BigInteger getShippingAddressId() {
        return this.shippingAddressId;
    }

    public void setShippingAddressId(final BigInteger shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public BigDecimal getSubtotalAmount() {
        return this.subtotalAmount;
    }

    public void setSubtotalAmount(final BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public BigDecimal getSubtotalProduct() {
        return this.subtotalProduct;
    }

    public void setSubtotalProduct(final BigDecimal subtotalProduct) {
        this.subtotalProduct = subtotalProduct;
    }

    public BigDecimal getSubtotalShipping() {
        return this.subtotalShipping;
    }

    public void setSubtotalShipping(final BigDecimal subtotalShipping) {
        this.subtotalShipping = subtotalShipping;
    }

    public BigDecimal getSubtotalTax() {
        return this.subtotalTax;
    }

    public void setSubtotalTax(final BigDecimal subtotalTax) {
        this.subtotalTax = subtotalTax;
    }

    public Set<AbstractOrderitem> getOrderitems() {
        return this.orderitems;
    }

    public void setOrderitems(final Set<AbstractOrderitem> orderitems) {
        this.orderitems = orderitems;
    }

    public AbstractOrder getOrder() {
        return this.order;
    }

    public void setOrder(final AbstractOrder order) {
        this.order = order;
    }

    public Shipmode getShipmode() {
        return this.shipmode;
    }

    public void setShipmode(final Shipmode shipmode) {
        this.shipmode = shipmode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

}