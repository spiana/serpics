package com.serpics.warehouse.persistence.dao;

import com.serpics.core.persistence.dao.BaseFactory;

public interface WarehouseFactory extends BaseFactory {
	public double fetchAvailability( String sku);
	

}
