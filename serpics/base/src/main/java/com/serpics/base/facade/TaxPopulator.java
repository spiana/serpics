package com.serpics.base.facade;

import org.springframework.beans.BeanUtils;

import com.serpics.base.data.model.Tax;
import com.serpics.base.facade.data.TaxData;
import com.serpics.core.facade.Populator;

public class TaxPopulator implements Populator<Tax, TaxData>{

	@Override
	public void populate(Tax source, TaxData target) {
			BeanUtils.copyProperties(source, target, "id", "store");
	}

	
}
