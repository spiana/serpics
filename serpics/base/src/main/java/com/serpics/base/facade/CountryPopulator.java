package com.serpics.base.facade;
import com.serpics.base.data.model.Country;
import com.serpics.base.facade.data.CountryData;
import com.serpics.core.facade.Populator;


public class CountryPopulator  implements Populator<Country, CountryData>{
	
	
	@Override
	public void populate(Country source, CountryData target) {
		target.setIso2Code(source.getIso2Code());
		target.setIso3Code(target.getIso3Code());
		
	}
}

