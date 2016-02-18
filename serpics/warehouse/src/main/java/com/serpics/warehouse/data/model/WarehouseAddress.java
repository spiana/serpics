package com.serpics.warehouse.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.serpics.membership.data.model.BaseAddress;

@Entity
public class WarehouseAddress extends BaseAddress{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="address_id")
	private Long id;
	
	@OneToOne(mappedBy="address", fetch=FetchType.LAZY , optional=false)
	private Warehouse warehouse;
}
