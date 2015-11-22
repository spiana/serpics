package com.serpics.catalog.data.repositories;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryRelationRepository extends Repository<CategoryRelation, CtentryRelationPK> {

	public List<CategoryRelation> findByParentCategory(Category parent);
	
	@Query("select count(c.childCategory) from CategoryRelation c where c.parentCategory = :category")
	public int getCountCategoryChild(@Param("category") Category parent);
	
}
