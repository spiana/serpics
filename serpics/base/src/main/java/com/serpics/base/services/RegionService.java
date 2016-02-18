package com.serpics.base.services;


import java.util.Set;

import com.serpics.base.data.model.Region;
import com.serpics.core.service.EntityService;

public interface RegionService extends EntityService<Region, Long>{

	public Set<Region> getRegionByCountry(Long countryId);
	public Region getRegionByCode(String isoCode);

}
