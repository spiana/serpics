package com.serpics.commerce.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.membership.data.model.AbstractStoreEntity;

/**
 * The persistent class for the shipmode database table.
 * 
 */
@Entity
@Table(name = "shipmode")
public class Shipmode extends AbstractStoreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipmode_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "name_string_id")
    private MultilingualString description ;   
    
    // bi-directional many-to-one association to Orderitem
    @OneToMany(mappedBy = "shipmode" , fetch=FetchType.LAZY)
    private Set<AbstractOrderitem> orderitems;

    // bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "shipmode" , fetch=FetchType.LAZY)
    private Set<AbstractOrder> orders;

       // bi-directional many-to-one association to Shipmodelookup
    @OneToMany(mappedBy = "shipmode", fetch=FetchType.LAZY )
    private Set<Shipmodelookup> shipmodelookups;

    // bi-directional many-to-one association to Shipping
    @OneToMany(mappedBy = "shipmode" , fetch=FetchType.LAZY)
    private Set<Shipping> shippings;

    // bi-directional many-to-one association to Suborder
    @OneToMany(mappedBy = "shipmode" , fetch=FetchType.LAZY)
    private Set<Suborder> suborders;

    public Shipmode() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long shipmodeId) {
        this.id = shipmodeId;
    }

      public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<AbstractOrderitem> getOrderitems() {
        return this.orderitems;
    }

    public void setOrderitems(final Set<AbstractOrderitem> orderitems) {
        this.orderitems = orderitems;
    }

    public Set<AbstractOrder> getOrders() {
        return this.orders;
    }

    public void setOrders(final Set<AbstractOrder> orders) {
        this.orders = orders;
    }

   
    public Set<Shipmodelookup> getShipmodelookups() {
        return this.shipmodelookups;
    }

    public void setShipmodelookups(final Set<Shipmodelookup> shipmodelookups) {
        this.shipmodelookups = shipmodelookups;
    }

    public Set<Shipping> getShippings() {
        return this.shippings;
    }

    public void setShippings(final Set<Shipping> shippings) {
        this.shippings = shippings;
    }

    public Set<Suborder> getSuborders() {
        return this.suborders;
    }

    public void setSuborders(final Set<Suborder> suborders) {
        this.suborders = suborders;
    }

	public MultilingualString getDescription() {
		return description;
	}

	public void setDescription(MultilingualString description) {
		this.description = description;
	}

}