package com.serpics.catalog.data.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.Repository;


public interface CategoryRelationRepository extends Repository<CategoryRelation, CtentryRelationPK> {

	public List<CategoryRelation> findByParentCategory(Category parent);
	
	@Query("select count(c.childCategory) from CategoryRelation c where c.parentCategory = :category")
	public int getCountCategoryChild(@Param("category") Category parent);
	
	@Query("select c.parentCategory from CategoryRelation c where c.childCategory = :category")
	public Set<Category> getParentCategories(@Param("category") Category child);
	
}
