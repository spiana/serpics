package com.serpics.warehouse.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.data.Repository;
import com.serpics.warehouse.data.model.Warehouse;

public interface WarehouseRepository extends Repository<Warehouse, Long>{

	@Query("select w from Warehouse w")
	public List<Warehouse> getAllWarehouses();
	
	@Query("select w from Inventory i join i.warehouse w  where w.store=:store and i.product=:product and (i.available - i.reserved) >= :needed order by precedence desc")
	public List<Warehouse> findPreferredForReserve(@Param("product")AbstractProduct product ,@Param("store") Store store , @Param("needed") Double needed);
	
	@Query("select w from Inventory i join i.warehouse w  where w.store=:store and i.product=:product and ( i.reserved >= :needed)  order by precedence desc")
	public List<Warehouse> findPreferredForRelease(@Param("product")AbstractProduct product ,@Param("store") Store store ,  @Param("needed") Double needed);
	
	@Query("select w from Warehouse w  where w.store=:store  and  w.forceInStock = 1 order by precedence desc")
	public List<Warehouse> findPreferredForced(@Param("store") Store store);
}
