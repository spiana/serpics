package com.serpics.catalog.data.repositories;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.Repository;

import java.util.List;


public interface CategoryRelationRepository extends Repository<CategoryRelation, CtentryRelationPK> {

	public List<CategoryRelation> findByParentCategory(Category parent);
	
}
