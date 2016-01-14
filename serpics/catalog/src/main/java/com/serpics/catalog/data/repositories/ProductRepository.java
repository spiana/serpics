package com.serpics.catalog.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Product;
import com.serpics.core.data.Repository;

public interface ProductRepository extends Repository<Product, Long> {

	@Query("select distinct p from Product p where (p.name.id in (select distinct s.id from MultilingualString s join s.map m where m.language = :language and m.text like ('%' || :searchText || '%'))) or (p.description.id in (select distinct s.id from MultilingualText s join s.map m where m.language = :language and m.text like ('%' || :searchText || '%'))) or  p.code like ('%' || :searchText || '%')")
	public Page<Product> findProductsBySearch(@Param("searchText") String searchText, @Param("language") String language, Pageable pageable);
	
}
