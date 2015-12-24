package com.serpics.warehouse.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.serpics.core.data.Repository;
import com.serpics.warehouse.data.model.Warehouse;

public interface WarehouseRepository extends Repository<Warehouse, Long>{

	@Query("select w from Warehouse w")
	public List<Warehouse> getAllWarehouses();
	
}
