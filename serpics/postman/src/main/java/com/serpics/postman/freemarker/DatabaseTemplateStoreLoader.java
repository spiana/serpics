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
package com.serpics.postman.freemarker;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.serpics.postman.model.TemplateStore;
import com.serpics.postman.model.TemplateType;
import com.serpics.postman.repositories.TemplateStoreRepository;

/**
 * Custom implementation of retriev template from Database bypassing freemarker process
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
@Component("templateStoreLoader")
public class DatabaseTemplateStoreLoader {

	

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TemplateStoreRepository templateStoreRepository;


	// Retrieve last template created by templateType
	public TemplateStore findTemplateStore(TemplateType templateType, String language) throws IOException {
		
		TemplateStore templateToReturn = null;
		
		logger.debug("Retrieve template with {} identifier", templateType.getId());
		
		Page<TemplateStore> templateStorePage = templateStoreRepository.findLastTemplateForType(
				templateType, new PageRequest(0, 1, Sort.Direction.DESC, "created"));
		
		
		logger.debug("Try to retrieve the type Template {} and locale {}",new Object[]{templateType.getName(),language});
		
		templateToReturn = templateStorePage.getContent().get(0);
		if (templateStorePage.getNumberOfElements() == 0
				|| StringUtils.isEmpty(templateToReturn.getTemplateMail().getText(language))) {
			logger.warn("Template not found for type Template {} and locale {}",new Object[]{templateType.getName(),language});
			return null;
		}

		return templateToReturn;
	}

}
