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
package com.serpics.vaadin.smc.ui.base;

import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.component.MediaComponent;

/**
 * @author spiana
 *
 */
@VaadinComponent("mediaEditForm")
public class MediaEditWindow extends EntityFormWindow<CtentryMedia>{

	/**
	 * 
	 */
	public MediaEditWindow() {
		super();
		MediaComponent<CtentryMedia> m = new MediaComponent<CtentryMedia>(CtentryMedia.class) {};
		addTab(m, "main");
	}
	
}
