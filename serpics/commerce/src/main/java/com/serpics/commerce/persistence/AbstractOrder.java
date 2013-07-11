package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
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

import com.serpics.base.persistence.Currency;
import com.serpics.membership.persistence.AbstractAddress;

/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pending", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractOrder extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static transient final String PENDING = "P";
	public static transient final String WAITING = "W"; // Waiting Payment
	public static transient final String COMPLETE = "C";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orders_id", unique = true, nullable = false)
	protected Long ordersId;

	@Column(name = "cookie", length = 250, unique = false, nullable = false)
	protected String cookie;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "currency_id")
	private Currency currency;

	@Column(name = "customer_id", nullable = false)
	protected Long customerId;

	@Column(name = "discount_amount", precision = 10, scale = 4)
	protected BigDecimal discountAmount = new BigDecimal(0);

	@Column(name = "discount_perc", precision = 10, scale = 4)
	protected double discountPerc = 0;

	@Column(name = "order_amount", nullable = false, precision = 10, scale = 4)
	protected BigDecimal orderAmount = new BigDecimal(0);

	// @Column(name="shipping_address_id")
	// private Long shippingAddressId;

	@Column(nullable = false, length = 2)
	protected String status;

	@Column(name = "store_id", nullable = false)
	protected Long storeId;

	@Column(name = "total_product", precision = 10, scale = 4)
	protected BigDecimal totalProduct = new BigDecimal(0);

	@Column(name = "total_shipping", precision = 10, scale = 4)
	protected BigDecimal totalShipping = new BigDecimal(0);

	@Column(name = "total_tax", precision = 10, scale = 4)
	protected BigDecimal totalTax = new BigDecimal(0);

	@Column(name = "user_id", nullable = false)
	protected Long userId;

	// bi-directional many-to-one association to Orderitem
	@OneToMany(mappedBy = "order", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	protected Set<Orderitem> orderitems = new HashSet<Orderitem>(0);

	// bi-directional many-to-one association to Shipmode
	@ManyToOne
	@JoinColumn(name = "shipmode_id")
	protected Shipmode shipmode;

	// bi-directional many-to-one association to OrdersAttribute
	@OneToMany(mappedBy = "order")
	protected Set<OrdersAttribute> ordersAttributes = new HashSet<OrdersAttribute>(0);

	// bi-directional many-to-one association to Suborder
	@OneToMany(mappedBy = "order")
	protected Set<Suborder> suborders = new HashSet<Suborder>(0);

	@ManyToOne
	@JoinColumn(name = "billing_address_id")
	protected AbstractAddress billingAddress;

	@ManyToOne
	@JoinColumn(name = "shipping_address_id")
	protected AbstractAddress shippingAddress;

	public AbstractOrder() {
	}

	public Long getOrdersId() {
		return this.ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getDiscountPerc() {
		return this.discountPerc;
	}

	public void setDiscountPerc(double discountPerc) {
		this.discountPerc = discountPerc;
	}

	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getTotalProduct() {
		return this.totalProduct;
	}

	public void setTotalProduct(BigDecimal totalProduct) {
		this.totalProduct = totalProduct;
	}

	public BigDecimal getTotalShipping() {
		return this.totalShipping;
	}

	public void setTotalShipping(BigDecimal totalShipping) {
		this.totalShipping = totalShipping;
	}

	public BigDecimal getTotalTax() {
		return this.totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<Orderitem> getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}

	public Shipmode getShipmode() {
		return this.shipmode;
	}

	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}

	public Set<OrdersAttribute> getOrdersAttributes() {
		return this.ordersAttributes;
	}

	public void setOrdersAttributes(Set<OrdersAttribute> ordersAttributes) {
		this.ordersAttributes = ordersAttributes;
	}

	public Set<Suborder> getSuborders() {
		return this.suborders;
	}

	public void setSuborders(Set<Suborder> suborders) {
		this.suborders = suborders;
	}

	public AbstractAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AbstractAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public AbstractAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AbstractAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}