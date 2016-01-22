package com.serpics.catalog.data.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Brand;
import com.serpics.core.data.Repository;

public interface BrandRepository extends Repository<Brand	, Long>{
	
	@Query("select count(p) from Product p where p.brand = :brand and p.buyable = true")
	public int getCountBrandProduct(@Param("brand") Brand brand);

}
