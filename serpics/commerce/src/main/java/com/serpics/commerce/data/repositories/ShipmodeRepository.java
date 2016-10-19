/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.commerce.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.core.data.Repository;

public interface ShipmodeRepository extends Repository<Shipmode, Long> {

	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.zipcode is null and s.district is null and s.region is null and s.country is null")
	public List<Shipmode> getShipmodeFromStore(@Param("store") Store store);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.zipcode = :zipcode")
	public List<Shipmode> getShipmodeFromZipCode(@Param("store") Store store, @Param("zipcode") String zipCode);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.country = :country and s.zipcode is null and s.district is null and s.region is null")
	public List<Shipmode> getShipmodeFromCountry(@Param("store") Store store, @Param("country") Country country);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.region = :region and s.zipcode is null and s.district is null")
	public List<Shipmode> getShipmodeFromRegion(@Param("store") Store store, @Param("region") Region region);
	
	@Query("select s.shipmode from Shipmodelookup s where s.store = :store and s.district = :district and s.zipcode is null")
	public List<Shipmode> getShipmodeFromDistrict(@Param("store") Store store, @Param("district") District district);
}
