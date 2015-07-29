package com.serpics.base.facade;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.core.facade.Populator;


public class GeocodePopulator  implements Populator<Geocode, GeocodeData>{
	
	
	@Override
	public void populate(Geocode source, GeocodeData target) {
		target.setDescription(source.getDescription().getText("it"));
		target.setName(source.getCode());
		target.setUuid(source.getUuid());
		
	}
}

