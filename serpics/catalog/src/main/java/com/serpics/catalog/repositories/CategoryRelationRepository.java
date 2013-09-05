package com.serpics.catalog.repositories;

import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.core.data.Repository;
import java.util.List;


public interface CategoryRelationRepository extends Repository<CategoryRelation, CtentryRelationPK> {

	public List<CategoryRelation> findByParentCategory(Category parent);
	
}
