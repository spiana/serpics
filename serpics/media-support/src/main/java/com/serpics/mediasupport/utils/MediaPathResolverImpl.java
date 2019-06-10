package com.serpics.mediasupport.utils;

import org.springframework.beans.factory.annotation.Value;

public class MediaPathResolverImpl implements MediaPathResolver {

	
	@Value("${media.base.path}")
	private String baseMediaPath;
	
	@Value("${media.base.webpath}")
	private String mediaWebPath ;
	
	@Override
	public String getBaseMediaPath() {
		return baseMediaPath;
	}

	@Override
	public String getMediaWebPath() {
		
		return mediaWebPath;
	}

}
