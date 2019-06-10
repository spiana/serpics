package com.serpics.base.mediasupport.utils;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.core.data.model.Catalog;
import com.serpics.mediasupport.utils.MediaPathResolverImpl;

public class CommerceMediaPathResolver extends MediaPathResolverImpl{

	@Resource(name="commerceEngine")
	CommerceEngine engine;
	
	
	@Override
	public String getBaseMediaPath() {
		String basePath = super.getBaseMediaPath();
		Catalog catalog = engine.getCurrentContext().getCatalog();
		if (catalog != null)
			basePath=FilenameUtils.concat(basePath, catalog.getId().toString());
			
		return basePath;
	}
}
