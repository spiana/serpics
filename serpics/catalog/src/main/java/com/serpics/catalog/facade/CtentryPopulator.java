package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.core.facade.Populator;

public class CtentryPopulator implements Populator<Ctentry, CtentryData> {

	@Override
	public void populate(Ctentry source, CtentryData target) {
		target.setCode(source.getCode());
		target.setUuid(source.getUuid());
		//target.setDescription(source.getDescription().getText("it"));
		
		
	}

}
