package com.serpics.base.facade;

import org.springframework.beans.BeanUtils;

import com.serpics.base.data.model.TaxCategory;
import com.serpics.base.facade.data.TaxCategoryData;
import com.serpics.core.facade.Populator;

public class TaxCategoryPopulator implements Populator<TaxCategory, TaxCategoryData>{

	@Override
	public void populate(TaxCategory source, TaxCategoryData target) {
			BeanUtils.copyProperties(source, target, "id", "store");
	}

	
}
