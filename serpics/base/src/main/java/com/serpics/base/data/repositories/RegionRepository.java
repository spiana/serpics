package com.serpics.base.data.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Region;
import com.serpics.core.data.Repository;


public interface RegionRepository extends Repository<Region, Long> {
	
	@Query("select r from Region r where r.name = :name")
	public Region getRegionByName(@Param("name") String name);

}