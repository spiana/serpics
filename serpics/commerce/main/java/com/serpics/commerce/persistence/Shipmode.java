package com.serpics.commerce.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the shipmode database table.
 * 
 */
@Entity
@Table(name = "shipmode")
public class Shipmode extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shipmode_id", unique = true, nullable = false)
	private Long shipmodeId;

	@Column(length = 1000)
	private String description;

	@Column(length = 100)
	private String name;

	// bi-directional many-to-one association to Orderitem
	@OneToMany(mappedBy = "shipmode")
	private Set<Orderitem> orderitems;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "shipmode")
	private Set<AbstractOrder> orders;

	// bi-directional many-to-one association to ShipmodeDescr
	@OneToMany(mappedBy = "shipmode")
	private Set<ShipmodeDescr> shipmodeDescrs;

	// bi-directional many-to-one association to Shipmodelookup
	@OneToMany(mappedBy = "shipmode")
	private Set<Shipmodelookup> shipmodelookups;

	// bi-directional many-to-one association to Shipping
	@OneToMany(mappedBy = "shipmode")
	private Set<Shipping> shippings;

	// bi-directional many-to-one association to Suborder
	@OneToMany(mappedBy = "shipmode")
	private Set<Suborder> suborders;

	public Shipmode() {
	}

	public Long getShipmodeId() {
		return this.shipmodeId;
	}

	public void setShipmodeId(Long shipmodeId) {
		this.shipmodeId = shipmodeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Orderitem> getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}

	public Set<AbstractOrder> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<AbstractOrder> orders) {
		this.orders = orders;
	}

	public Set<ShipmodeDescr> getShipmodeDescrs() {
		return this.shipmodeDescrs;
	}

	public void setShipmodeDescrs(Set<ShipmodeDescr> shipmodeDescrs) {
		this.shipmodeDescrs = shipmodeDescrs;
	}

	public Set<Shipmodelookup> getShipmodelookups() {
		return this.shipmodelookups;
	}

	public void setShipmodelookups(Set<Shipmodelookup> shipmodelookups) {
		this.shipmodelookups = shipmodelookups;
	}

	public Set<Shipping> getShippings() {
		return this.shippings;
	}

	public void setShippings(Set<Shipping> shippings) {
		this.shippings = shippings;
	}

	public Set<Suborder> getSuborders() {
		return this.suborders;
	}

	public void setSuborders(Set<Suborder> suborders) {
		this.suborders = suborders;
	}

}