package com.serpics.catalog.data.repositories;

import java.util.List;

import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.data.Repository;

public interface Category2ProductRepository extends Repository<CategoryProductRelation, CtentryRelationPK>  {
	public List<CategoryProductRelation> findByChildProduct(Product product);
}
