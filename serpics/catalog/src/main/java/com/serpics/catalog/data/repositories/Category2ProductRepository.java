package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.data.Repository;

public interface Category2ProductRepository extends Repository<CategoryProductRelation, CtentryRelationPK>  {
	
	@Query("select c.childProduct from CategoryProductRelation c where c.parentCategory = :category")
	public Page<Product> findProductsByCategory(@Param("category") Category category,Pageable pageable);
	
	public List<CategoryProductRelation> findByChildProduct(Product product);
}
