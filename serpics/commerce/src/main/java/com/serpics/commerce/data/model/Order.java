package com.serpics.commerce.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.serpics.commerce.OrderStatus;

@Entity(name = "Order")
@DiscriminatorValue(value = "0")
public class Order extends AbstractOrder {

    public Order() {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "orderNumber", unique = true, nullable = true, length = 100)
    protected String orderNumber;

     @Column(name = "pay_amount", nullable = true, precision = 10, scale = 4)
    private Double payAmount = new Double(0);

    // bi-directional many-to-one association to Orderpayment
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Orderpayment> orderpayments = new HashSet<Orderpayment>(0);

    // bi-directional many-to-one association to Orderitem
    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity=Orderitem.class)
    protected Set<Orderitem> orderitems = new HashSet<Orderitem>(0);

    
    public Set<Orderpayment> getOrderpayments() {
        return orderpayments;
    }

    public void setOrderpayments(final Set<Orderpayment> orderpayments) {
        this.orderpayments = orderpayments;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(final Double payAmount) {
        this.payAmount = payAmount;
    }

    @PrePersist
    public void prepareNewOrder() {
        if (this.status == null) {
            this.status = OrderStatus.CREATED;
        }
       }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

	public Set<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderItems(Set<Orderitem> items) {
		this.orderitems = items;
	}

	@Override
	public Set<? extends AbstractOrderitem> getItems() {
	
		return getOrderitems();
	}
 
  

}
