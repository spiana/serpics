package com.serpics.base.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.DistrictData;



public interface DistrictFacade {
	public Page<DistrictData> findAll(Pageable page);
	public DistrictData addDistrict( DistrictData district);
	public DistrictData findDistrictByUuid(String districtUuid);
	public List<DistrictData> findDistrictByCountry(Long countryId);
	public List<DistrictData> findDistrictByRegion(Long regionId);
	public DistrictData findDistrictByCode(String name);
}
