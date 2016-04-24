package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Catalog2StoreRelPK;
import com.serpics.catalog.data.model.Catalog2StoreRelation;
import com.serpics.core.data.Repository;

public interface Catalog2StoreRelationRepository extends Repository<Catalog2StoreRelation, Catalog2StoreRelPK> {

	@Query("select m.catalog from Catalog2StoreRelation m where m.store = :store and selected = true")
	public List<Catalog> findPrimaryCatalog(@Param("store") Store store);
}
