package com.serpics.commerce.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.core.data.Repository;

public interface ShipmodeRepository extends Repository<Shipmode, Long> {

	@Query("select s.shipmode from Shipmodelookup s where s.store = :store")
	public List<Shipmode> getShipmodeFromStore(@Param("store") Store store);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.zipcode = :zipcode")
	public List<Shipmode> getShipmodeFromZipCode(@Param("store") Store store, @Param("zipcode") String zipCode);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.country = :country")
	public List<Shipmode> getShipmodeFromCountry(@Param("store") Store store, @Param("country") Country country);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.region = :region")
	public List<Shipmode> getShipmodeFromRegion(@Param("store") Store store, @Param("region") Region region);
}
