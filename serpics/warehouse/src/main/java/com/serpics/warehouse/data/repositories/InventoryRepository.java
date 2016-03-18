package com.serpics.warehouse.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.data.Repository;
import com.serpics.warehouse.data.model.Inventory;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryRepository extends Repository<Inventory, Long>{
	
	@Query("select coalesce(sum(i.available - i.reserved), 0) from Inventory i join i.warehouse w  where w.store=:store and i.product=:product")
	public Double getAvailable(@Param("product")Product product , @Param("store") Store store);
	
	
	@Query("select coalesce(sum(i.available - i.reserved),0) from Inventory i where i.warehouse=:warehouse and i.product=:product")
	public Double getAvailableByWarehouse(@Param("product")Product product , @Param("warehouse") Warehouse warehouse);
	
	@Query("select coalesce(i.reserved,0) from Inventory i where i.warehouse=:warehouse and i.product=:product")
	public Double getReservedByWarehouse(@Param("product")Product product , @Param("warehouse") Warehouse warehouse);

	
}
