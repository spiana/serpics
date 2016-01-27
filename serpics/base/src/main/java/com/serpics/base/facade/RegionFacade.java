package com.serpics.base.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.RegionData;



public interface RegionFacade {
	public Page<RegionData> findAll(Pageable page);
	public RegionData addRegion(RegionData region);
	public RegionData findRegionByUuid(String regionUuid);
}
