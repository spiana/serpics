package com.serpics.catalog.data.interceptors;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.MultilingualText;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.core.data.SaveInterceptor;

public class CtentrySaveInterceptor implements SaveInterceptor<Ctentry>{

	@Override
	public void beforeSave(Ctentry entity) {
		if (entity.getName() == null){
			entity.setName(new MultilingualString("en" , "en"));
		}
		if (entity.getDescription() == null)
			entity.setDescription(new MultilingualText("en" , "en"));
	}

	@Override
	public void afterSave(Ctentry entity) {
		System.out.println(entity);
	}

}
