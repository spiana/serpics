package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.core.data.Repository;

public interface CatalogRepository extends Repository<Catalog, Long> {

	@Query("select c from Catalog c  where c.published=1") 
	public List<Catalog> findPublished();

	public Catalog findByCode(String code);
	
}