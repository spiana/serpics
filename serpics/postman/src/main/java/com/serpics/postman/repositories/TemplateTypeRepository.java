package com.serpics.postman.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.core.data.Repository;
import com.serpics.postman.model.TemplateType;

public interface TemplateTypeRepository extends Repository<TemplateType,Long>{
	
	@Query("select ts from TemplateType ts where ts.name= :name")
	public TemplateType getTemplateTypeByName(@Param("name") String name);
}
