package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.core.facade.Populator;

public class CategoryPopulator extends CtentryPopulator implements Populator<Ctentry, CtentryData> {

	@Override
	public void populate(Ctentry source, CtentryData target) {
		super.populate(source, target);
		//target.setCode(source.getCode());
		//target.setDescription(source.getDescription().getText("it"));
		
		
	}

}
