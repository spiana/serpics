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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the orderpayment database table.
 * 
 */
@Entity
@Table(name = "orderpayment")
public class Orderpayment extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderpayment_id", unique = true, nullable = false)
    private Long id;

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

    public Long getId() {
        return this.id;
    }

    public void setId(final Long orderpaymentId) {
        this.id = orderpaymentId;
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