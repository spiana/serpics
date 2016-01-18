package com.serpics.catalog.data.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.data.Repository;

public interface BrandRepository extends Repository<Brand	, Long>{
	
	@Query("select count(p) from Product p where p.brand = :brand and p.buyable = '1'")
	public int getCountBrandProduct(@Param("brand") Brand brand);
	
	@Query("select distinct p from Product p where p.brand = :brand and p.buyable = '1'")
	public Page<Product> findProductsByBrand(@Param("brand") Brand brand,Pageable pageable);

}
