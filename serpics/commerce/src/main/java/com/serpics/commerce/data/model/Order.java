/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
import javax.validation.constraints.Size;

import com.serpics.commerce.OrderStatus;

@Entity(name = "Order")
@DiscriminatorValue(value = "0")
public class Order extends AbstractOrder {

    public Order() {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Size(max = 100, message = "{order.orderNumber.size}")
    @Column(name = "orderNumber", unique = true, nullable = true, length = 100)
    protected String orderNumber;

    @Column(name = "pay_amount", nullable = true, precision = 10, scale = 4)
    private Double payAmount = new Double(0);

    // bi-directional many-to-one association to Orderpayment
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Orderpayment> orderpayments = new HashSet<Orderpayment>(0);

   
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

    @Override
    public Set<Orderitem> getItems() {
    	return (Set<Orderitem>) super.getItems();
    }
}
