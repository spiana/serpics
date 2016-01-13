package com.serpics.commerce.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "0")
public class Orderitem extends AbstractOrderitem {

	private static final long serialVersionUID = -3290337831602573616L;
	
	 @ManyToOne
	 @JoinColumn(name = "order_id", nullable = false)
	 protected Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
