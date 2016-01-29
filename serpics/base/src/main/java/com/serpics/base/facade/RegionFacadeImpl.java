package com.serpics.base.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.RegionData;
import com.serpics.base.services.CountryService;
import com.serpics.base.services.RegionService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.stereotype.StoreFacade;


@StoreFacade("regionFacade")
@Transactional(readOnly=true)
public class RegionFacadeImpl implements RegionFacade {
	@Autowired
	RegionService regionService;
	
	@Autowired
	CountryService countryService;
	
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
	
	
	@Override
	@Transactional
	public RegionData addRegion(RegionData data) {
		Country c = countryService.findByUUID(data.getCountry().getUuid());
		String locale = "it";
		//if(engine.getCurrentContext() != null) locale = engine.getCurrentContext().getLocale().getLanguage();
		final MultilingualString description = new MultilingualString(locale, data.getDescription());
		Region r = new Region();
		r.setName(data.getName());
		r.setDescription(description);
		r.setCountry(c);
		r = regionService.create(r);
		data = regionConverter.convert(r);
		return data;
	}


	@Override
	public RegionData findRegionByUuid(String regionUuid) {

		return regionConverter.convert(regionService.findByUUID(regionUuid));
	}
	
	@Override
	public List<RegionData> findRegionByCountry(Long countryId){
		List<RegionData> regionList = new ArrayList<RegionData>();
		for (Region region : regionService.getRegionByCountry(countryId)){
			regionList.add(regionConverter.convert(region));
		}
		return regionList;
	}
}
