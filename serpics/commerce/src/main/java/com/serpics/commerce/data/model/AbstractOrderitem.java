package com.serpics.commerce.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.TaxCategory;
import com.serpics.catalog.data.model.AbstractProduct;

@Entity
@Table(name = "orderitems")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pending", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractOrderitem extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderitem_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "discount_amount", precision = 10, scale = 4)
    protected Double discountAmount = new Double(0);

    @Column(name = "discount_perc", precision = 5, scale = 2)
    protected double discountPerc;

    @Column(name = "discount_perc1", precision = 5, scale = 2)
    protected double discountPerc1;

    @Column(name = "discount_perc2", precision = 5, scale = 2)
    protected double discountPerc2;

    @Column(nullable = false, precision = 5, scale = 2)
    protected double quantity;

    @Column(name = "shipping_address_id")
    protected Long shippingAddressId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    protected AbstractProduct product;

    @NotNull(message ="{abstractOrderitem.sku.notnull}")
    @Size(max=250, message="{abstractOrderitem.sku.size}")
    @Column(nullable = false, length = 250)
    protected String sku;

    @Column(name = "sku_cost", precision = 10, scale = 4)
    protected Double skuCost = new Double(0);

    @Size(max=1000, message ="{abstractOrderitem.skuDescription.size}")
    @Column(name = "sku_description", length = 1000)
    protected String skuDescription;

    @Column(name = "sku_net_price", nullable = false, precision = 10, scale = 4)
    protected Double skuNetPrice = new Double(0);

    @Column(name = "sku_price", nullable = false, precision = 10, scale = 4)
    protected Double skuPrice = new Double(0);

    @Column(name = "shipping_cost", nullable = false, precision = 10, scale = 4)
    protected Double shippingCost = new Double(0);

    // bi-directional many-to-one association to Shipmode
    @ManyToOne
    @JoinColumn(name = "shipmode_id")
    protected Shipmode shipmode;

    // bi-directional many-to-one association to Suborder
    @ManyToOne
    @JoinColumn(name = "suborders_id", nullable = true)
    protected Suborder suborder;

    // bi-directional many-to-one association to OrderitemsAttribute
    @OneToMany(mappedBy = "orderitem", cascade = CascadeType.REMOVE)
    protected Set<OrderitemsAttribute> orderitemsAttributes;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "taxcategory_id")
    private TaxCategory taxcategory;
    
    
    public AbstractOrderitem() {
    }

    public AbstractOrderitem(final String sku, final String sku_description, final double quantity, final Double price) {
        this.sku = sku;
        this.skuDescription = sku_description;
        this.quantity = quantity;
        this.skuCost = this.skuNetPrice = this.skuPrice = price;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long orderitemsId) {
        this.id = orderitemsId;
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

	public TaxCategory getTaxcategory() {
		return taxcategory;
	}

	public void setTaxcategory(TaxCategory taxcategory) {
		this.taxcategory = taxcategory;
	}

}