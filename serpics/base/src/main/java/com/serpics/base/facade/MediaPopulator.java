/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.base.facade;

import com.serpics.base.data.model.Media;
import com.serpics.base.facade.data.MediaData;
import com.serpics.core.facade.Populator;

public class MediaPopulator implements Populator<Media, MediaData> {

	@Override
	public void populate(Media source, MediaData target) {
		if(source.getSource() != null)
			target.setSource(source.getSource());
		
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
