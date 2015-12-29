package com.serpics.commerce.data.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.data.model.Currency;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;

/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pending", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractOrder extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static transient final String PENDING = "P";
    public static transient final String WAITING = "W"; // Waiting Payment
    public static transient final String COMPLETE = "C";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", unique = true, nullable = false)
    protected Long id;

    @Column(name = "cookie", length = 250, unique = false, nullable = true)
    protected String cookie;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Currency.class)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    protected Member customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "discount_amount", precision = 10, scale = 4)
    protected Double discountAmount ;

    @Column(name = "discount_perc", precision = 10, scale = 4)
    protected double discountPerc = 0;

    @Column(name = "order_amount", nullable = true, precision = 10, scale = 4)
    protected Double orderAmount ;
    
    // @Column(name="shipping_address_id")
    // private Long shippingAddressId;

    @NotNull
    @Column(nullable = false, length = 2)
    protected String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    protected Store store;

    @Column(name = "total_product", precision = 10, scale = 4)
    protected Double totalProduct;

    @Column(name = "total_service", precision = 10, scale = 4)
    protected Double totalService ;

    @Column(name = "total_shipping", precision = 10, scale = 4)
    protected Double totalShipping ;

    @Column(name = "total_cost", precision = 10, scale = 4)
    protected Double totalCost ;

    @Column(name = "total_price", precision = 10, scale = 4)
    protected Double totalPrice ;
    
    @Column(name = "total_tax", precision = 10, scale = 4)
    protected Double totalTax;

    // bi-directional many-to-one association to Shipmode
    @ManyToOne(fetch=FetchType.LAZY , optional=true)
    @JoinColumn(name = "shipmode_id")
    protected Shipmode shipmode;

    // bi-directional many-to-one association to OrdersAttribute
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    protected Set<OrdersAttribute> ordersAttributes = new HashSet<OrdersAttribute>(0);

    // bi-directional many-to-one association to Suborder
    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    protected Set<Suborder> suborders = new HashSet<Suborder>(0);

//    @ManyToOne(fetch=FetchType.LAZY )
//    @JoinColumn(name = "billing_address_id" )
    @OneToOne(optional=true)
    @JoinColumn(
        name="billing_address_id", unique=true, nullable=true, updatable=true)
    protected Address  billingAddress;

//    @ManyToOne(fetch=FetchType.LAZY )
//    @JoinColumn(name = "shipping_address_id")
    
    @OneToOne(optional=true)
    @JoinColumn(
        name="shipping_address_id", unique=true, nullable=true, updatable=true)
    protected Address shippingAddress;

    // bi-directional many-to-one association to Orderitem
    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
    protected Set<AbstractOrderitem> orderitems = new HashSet<AbstractOrderitem>(0);

    public AbstractOrder() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long orderId) {
        this.id = orderId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public double getDiscountPerc() {
        return this.discountPerc;
    }

    public void setDiscountPerc(final double discountPerc) {
        this.discountPerc = discountPerc;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Shipmode getShipmode() {
        return this.shipmode;
    }

    public void setShipmode(final Shipmode shipmode) {
        this.shipmode = shipmode;
    }

    public Set<OrdersAttribute> getOrdersAttributes() {
        return this.ordersAttributes;
    }

    public void setOrdersAttributes(final Set<OrdersAttribute> ordersAttributes) {
        this.ordersAttributes = ordersAttributes;
    }

    public Set<Suborder> getSuborders() {
        return this.suborders;
    }

    public void setSuborders(final Set<Suborder> suborders) {
        this.suborders = suborders;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(final Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(final Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(final String cookie) {
        this.cookie = cookie;
    }

    public Member getCustomer() {
        return customer;
    }

    public void setCustomer(final Member customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    public Set<? extends AbstractOrderitem> getOrderitems() {
        return orderitems;
    }

    @SuppressWarnings("unchecked")
	public void setOrderitems(final Set<? extends AbstractOrderitem> orderitems) {
        this.orderitems = (Set<AbstractOrderitem>) orderitems;
    }

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Double totalProduct) {
		this.totalProduct = totalProduct;
	}

	public Double getTotalService() {
		return totalService;
	}

	public void setTotalService(Double totalService) {
		this.totalService = totalService;
	}

	public Double getTotalShipping() {
		return totalShipping;
	}

	public void setTotalShipping(Double totalShipping) {
		this.totalShipping = totalShipping;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

}