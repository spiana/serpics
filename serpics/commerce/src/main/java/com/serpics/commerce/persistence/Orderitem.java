package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.serpics.catalog.persistence.AbstractProduct;

@Entity
@Table(name = "orderitems")
public class Orderitem extends com.serpics.core.persistence.jpa.Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderitems_id", unique = true, nullable = false)
    private Long orderitemsId;

    @Column(name = "discount_amount", precision = 10, scale = 4)
    private Double discountAmount = new Double(0);

    @Column(name = "discount_perc", precision = 5, scale = 2)
    private double discountPerc;

    @Column(name = "discount_perc1", precision = 5, scale = 2)
    private double discountPerc1;

    @Column(name = "discount_perc2", precision = 5, scale = 2)
    private double discountPerc2;

    @Column(nullable = false, precision = 5, scale = 2)
    private double quantity;

    @Column(name = "shipping_address_id")
    private Long shippingAddressId;

    @Column(name = "product_id", nullable = true)
    AbstractProduct product;

    @Column(nullable = false, length = 250)
    private String sku;

    @Column(name = "sku_cost", precision = 10, scale = 4)
    private Double skuCost = new Double(0);

    @Column(name = "sku_description", length = 1000)
    private String skuDescription;

    @Column(name = "sku_net_price", nullable = false, precision = 10, scale = 4)
    private Double skuNetPrice = new Double(0);

    @Column(name = "sku_price", nullable = false, precision = 10, scale = 4)
    private Double skuPrice = new Double(0);

    @Column(name = "shipping_cost", nullable = false, precision = 10, scale = 4)
    private Double shippingCost = new Double(0);

    // bi-directional many-to-one association to Order
    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private AbstractOrder order;

    // bi-directional many-to-one association to Shipmode
    @ManyToOne
    @JoinColumn(name = "shipmode_id")
    private Shipmode shipmode;

    // bi-directional many-to-one association to Suborder
    @ManyToOne
    @JoinColumn(name = "suborders_id")
    private Suborder suborder;

    // bi-directional many-to-one association to OrderitemsAttribute
    @OneToMany(mappedBy = "orderitem")
    private Set<OrderitemsAttribute> orderitemsAttributes;

    public Orderitem() {
    }

    public Orderitem(final String sku, final String sku_description, final double quantity, final Double price) {
        this.sku = sku;
        this.skuDescription = sku_description;
        this.quantity = quantity;
        this.skuCost = this.skuNetPrice = this.skuPrice = price;

    }

    public Long getOrderitemsId() {
        return this.orderitemsId;
    }

    public void setOrderitemsId(final Long orderitemsId) {
        this.orderitemsId = orderitemsId;
    }

    public double getDiscountPerc() {
        return this.discountPerc;
    }

    public void setDiscountPerc(final double discountPerc) {
        this.discountPerc = discountPerc;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final double quantity) {
        this.quantity = quantity;
    }

    public Long getShippingAddressId() {
        return this.shippingAddressId;
    }

    public void setShippingAddressId(final Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(final String sku) {
        this.sku = sku;
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

    public Suborder getSuborder() {
        return this.suborder;
    }

    public void setSuborder(final Suborder suborder) {
        this.suborder = suborder;
    }

    public Set<OrderitemsAttribute> getOrderitemsAttributes() {
        return this.orderitemsAttributes;
    }

    public void setOrderitemsAttributes(final Set<OrderitemsAttribute> orderitemsAttributes) {
        this.orderitemsAttributes = orderitemsAttributes;
    }

    public Double getSkuCost() {
        return skuCost;
    }

    public void setSkuCost(final Double skuCost) {
        this.skuCost = skuCost;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(final String skuDescription) {
        this.skuDescription = skuDescription;
    }

    public Double getSkuNetPrice() {
        return skuNetPrice;
    }

    public void setSkuNetPrice(final Double skuNetPrice) {
        this.skuNetPrice = skuNetPrice;
    }

    public Double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(final Double skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public AbstractProduct getProduct() {
        return product;
    }

    public void setProduct(final AbstractProduct product) {
        this.product = product;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(final Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getDiscountPerc1() {
        return discountPerc1;
    }

    public void setDiscountPerc1(final double discountPerc1) {
        this.discountPerc1 = discountPerc1;
    }

    public double getDiscountPerc2() {
        return discountPerc2;
    }

    public void setDiscountPerc2(final double discountPerc2) {
        this.discountPerc2 = discountPerc2;
    }

    public void setDiscountAmount(final Double discountAmount) {
        this.discountAmount = discountAmount;
    }

}