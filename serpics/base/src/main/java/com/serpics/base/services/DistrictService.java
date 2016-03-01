package com.serpics.base.services;


import java.util.Set;

import com.serpics.base.data.model.District;
import com.serpics.core.service.EntityService;

public interface DistrictService extends EntityService<District, Long>{

	public Set<District> getDistrictByCountry(Long countryId);
	public Set<District> getDistrictByRegion(Long regionId);
	public District getDistrictByCode(String isoCode);

}
