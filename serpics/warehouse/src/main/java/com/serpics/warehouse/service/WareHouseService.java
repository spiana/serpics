package com.serpics.warehouse.service;

import com.serpics.catalog.data.model.Product;
import com.serpics.warehouse.data.model.Warehouse;

public interface WareHouseService {

	public Warehouse findPreferredForReserve(Product product , Double needed);
	public Warehouse findPreferredForRelease(Product product, Double needed);
	public Warehouse findPreferredForced();
	
}
