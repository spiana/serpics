package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.Media;
import com.serpics.catalog.facade.data.MediaData;
import com.serpics.core.facade.Populator;

public class MediaPopulator implements Populator<Media, MediaData> {

	@Override
	public void populate(Media source, MediaData target) {
		if(source.getSrc() != null)
			target.setSrc(source.getSrc());
		target.setName(source.getName());
		
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setSequence((int)source.getSequence());
		target.setDescription(source.getDescription().getText("it"));
		target.setContentType(source.getContentType());
		
	}

}
