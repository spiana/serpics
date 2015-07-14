package com.serpics.base.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.RegionData;
import com.serpics.base.services.RegionService;
import com.serpics.core.facade.AbstractPopulatingConverter;

public class RegionFacadeImpl implements RegionFacade {
	@Autowired
	RegionService regionService;
	
	@Resource(name="regionConverter")
	AbstractPopulatingConverter<Region, RegionData> regionConverter;
	
	@Override
	public Page<RegionData> findAll(Pageable page) {
		Page<Region> regions = regionService.findAll(page); 
		
		List<RegionData> clist = new ArrayList<RegionData>();
		for (Region country : regions.getContent()) {
			clist.add(regionConverter.convert(country));
		}
		
		Page<RegionData> cdata= new PageImpl<RegionData>(clist ,page , regions.getTotalElements());
		return cdata;
	}
}
