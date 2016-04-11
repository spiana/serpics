package com.serpics.warehouse.service;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.warehouse.data.model.Warehouse;

public interface WareHouseService {

	public Warehouse findPreferredForReserve(AbstractProduct product , Double needed);
	public Warehouse findPreferredForRelease(AbstractProduct product, Double needed);
	public Warehouse findPreferredForced();
	
}
