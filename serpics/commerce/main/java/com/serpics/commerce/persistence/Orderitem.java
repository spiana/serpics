package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.serpics.base.persistence.Currency;
import com.serpics.catalog.persistence.AbstractProduct;

@Entity
@Table(name = "orderitems")
public class Orderitem extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderitems_id", unique = true, nullable = false)
	private Long orderitemsId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "currency_id")
	private Currency currency;

	@Column(name = "store_id", nullable = false)
	private Long storeId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "discount_amount", precision = 10, scale = 4)
	private final Double discountAmount = new Double(0);

	@Column(name = "discount_perc", precision = 5, scale = 2)
	private double discountPerc;

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

	@Column(nullable = false, length = 2)
	private String status;

	// bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name = "orders_id", nullable = true)
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

	public Orderitem(String sku, String sku_description, double quantity, Double price) {
		this.sku = sku;
		this.skuDescription = sku_description;
		this.quantity = quantity;
		this.skuCost = this.skuNetPrice = this.skuPrice = price;
		this.status = AbstractOrder.PENDING;
	}

	public Long getOrderitemsId() {
		return this.orderitemsId;
	}

	public void setOrderitemsId(Long orderitemsId) {
		this.orderitemsId = orderitemsId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public double getDiscountPerc() {
		return this.discountPerc;
	}

	public void setDiscountPerc(double discountPerc) {
		this.discountPerc = discountPerc;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Long getShippingAddressId() {
		return this.shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AbstractOrder getOrder() {
		return this.order;
	}

	public void setOrder(AbstractOrder order) {
		this.order = order;
	}

	public Shipmode getShipmode() {
		return this.shipmode;
	}

	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}

	public Suborder getSuborder() {
		return this.suborder;
	}

	public void setSuborder(Suborder suborder) {
		this.suborder = suborder;
	}

	public Set<OrderitemsAttribute> getOrderitemsAttributes() {
		return this.orderitemsAttributes;
	}

	public void setOrderitemsAttributes(Set<OrderitemsAttribute> orderitemsAttributes) {
		this.orderitemsAttributes = orderitemsAttributes;
	}

	public Double getSkuCost() {
		return skuCost;
	}

	public void setSkuCost(Double skuCost) {
		this.skuCost = skuCost;
	}

	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	public Double getSkuNetPrice() {
		return skuNetPrice;
	}

	public void setSkuNetPrice(Double skuNetPrice) {
		this.skuNetPrice = skuNetPrice;
	}

	public Double getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public AbstractProduct getProduct() {
		return product;
	}

	public void setProduct(AbstractProduct product) {
		this.product = product;
	}

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}