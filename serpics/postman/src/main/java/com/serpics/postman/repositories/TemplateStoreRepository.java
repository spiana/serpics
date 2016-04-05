package com.serpics.postman.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.core.data.Repository;
import com.serpics.postman.model.TemplateStore;

public interface TemplateStoreRepository extends Repository<TemplateStore, Long> {

	@Query("select ts from TemplateStore ts where ts.templateType.id= :templateType")
	public Page<TemplateStore> findLastTemplateForType(@Param("templateType") String template,Pageable pageable);
	
	
}
