package com.serpics.base.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.RegionData;



public interface RegionFacade {
	public Page<RegionData> findAll(Pageable page);
	public RegionData addRegion( RegionData region);
	public RegionData findRegionByUuid(String regionUuid);
	public List<RegionData> findRegionByCountry(Long countryId);
	public RegionData findRegionByName(String name);
}
