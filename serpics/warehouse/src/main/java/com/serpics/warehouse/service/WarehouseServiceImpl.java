package com.serpics.warehouse.service;

import java.util.List;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.repositories.WarehouseRepository;

@StoreService("warehouseService")
public class WarehouseServiceImpl extends AbstractService<CommerceSessionContext> implements WareHouseService {
	
	@Resource
	WarehouseRepository warehouseRepository;

	@Override
	public Warehouse findPreferredForReserve(Product  product , Double needed) {
		List<Warehouse> _w = warehouseRepository.findPreferredForReserve(product, (Store) getCurrentContext().getStoreRealm() , needed);
		if (_w.isEmpty())
		 return null;

		return _w.get(0); 
		
	}

	@Override
	public Warehouse  findPreferredForRelease(Product product , Double needed) {
		List<Warehouse> _w = warehouseRepository.findPreferredForRelease(product, (Store) getCurrentContext().getStoreRealm() , needed);
		if (_w.isEmpty())
		 return null;

		return _w.get(0); 
	}

	@Override
	public Warehouse findPreferredForced() {
		List<Warehouse> _w = warehouseRepository.findPreferredForced((Store) getCurrentContext().getStoreRealm());
		if (_w.isEmpty())
			 return null;

		return _w.get(0); 
	}

}
