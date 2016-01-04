package com.serpics.base.facade;

import com.serpics.base.data.model.Media;
import com.serpics.base.facade.data.MediaData;
import com.serpics.core.facade.Populator;

public class MediaPopulator implements Populator<Media, MediaData> {

	@Override
	public void populate(Media source, MediaData target) {
		if(source.getSource() != null)
			target.setSrc(source.getSource());
		
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
