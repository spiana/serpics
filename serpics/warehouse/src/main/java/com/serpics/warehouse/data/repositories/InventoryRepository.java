package com.serpics.warehouse.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Store;
import com.serpics.warehouse.data.model.Inventory;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryRepository extends Repository<Inventory, Long>{
	
	@Query("select sum(i.available - i.reserved) from Inventory i join i.warehouse w  where w.store=:store and i.product=:product")
	public Double getAvailable(@Param("product")AbstractProduct product , @Param("store") Store store);
	
	
	@Query("select sum(i.available - i.reserved) from Inventory i where i.warehouse=:warehouse and i.product=:product")
	public Double getAvailableByWarehouse(@Param("product")AbstractProduct product , @Param("warehouse") Warehouse warehouse);
}
