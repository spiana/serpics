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
import java.io.Reader;
import java.io.StringReader;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.postman.data.CoupleStore;
import com.serpics.postman.model.TemplateStore;
import com.serpics.postman.repositories.TemplateStoreRepository;

import freemarker.cache.TemplateLoader;

/**
 * custom implementation of the freemarker template loader to exploit the cache
 * mechanism implemented in the template engine framework.
 * 
 * @author alessandro.marasco@tinvention.net
 * @deprecated Prefer to use com.serpics.postman.freemarker.DatabaseTemplateStoreLoader for bypassing Freemarker cache
 */
public class DatabaseTemplateLoader implements TemplateLoader {

	private static final String LOCALE_SEPARATOR = "_";

	private static final Locale DEFAULT_LANGUAGE = Locale.ENGLISH;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TemplateStoreRepository templateStoreRepository;

	@Autowired
	CommerceEngine commerceEngine;

	@Override
	public void closeTemplateSource(Object arg0) throws IOException {
		// NOOP
	}

	// Retrieve last template created by templateType
	@Override
	public Object findTemplateSource(final String typeTemplateId) throws IOException {
		
		logger.debug("Retrieve template with {} identifier", typeTemplateId);
		
		CoupleStore<String, String> templateParsed = parseIdTypeTemplate(typeTemplateId);

		logger.debug("After parser idTypeTemplate, the result is {}", templateParsed);
		
		Page<TemplateStore> templateStorePage = templateStoreRepository.findLastTemplateForType(
				templateParsed.getLeftValue(), new PageRequest(0, 1, Sort.Direction.DESC, "created"));

		CommerceSessionContext commerceSession = commerceEngine.getCurrentContext();
		String language = DEFAULT_LANGUAGE.getLanguage();
		
		if (commerceSession == null) {
			logger.warn("Local in current context is not present, use the default {}",language);
		}else{
			
			language = commerceSession.getLocale().getLanguage();
		}
		
		logger.debug("Try to retrieve the type Template {} for store {} and locale {}",new Object[]{templateParsed.getLeftValue(),commerceSession.getRealm(),language});
		
		if (templateStorePage.getNumberOfElements() == 0 || StringUtils.isEmpty(language)
				|| StringUtils.isEmpty(templateStorePage.getContent().get(0).getTemplateMail().getText(language))) {
			logger.warn("Template not found for type Template {} for store {} and locale {}",new Object[]{templateParsed.getLeftValue(),commerceSession.getRealm(),language});
			return null;
		}

		return templateStorePage;
	}

	@Override
	public long getLastModified(Object arg0) {
		Assert.isInstanceOf(TemplateStore.class, arg0, "Template in use is not a Template Store instance");
		TemplateStore template = (TemplateStore) arg0;

		template = templateStoreRepository.findOne(template.getId());
		return template.getUpdated().getTime();
	}

	@Override
	public Reader getReader(Object template, String locale) throws IOException {
		
		Assert.isInstanceOf(TemplateStore.class, template, "Template in use is not a Template Store instance");
		CommerceSessionContext commerceSession = commerceEngine.getCurrentContext();
		Assert.notNull(commerceSession, "Local Context is not present");
		String language = DEFAULT_LANGUAGE.getLanguage();
		if (commerceSession.getLocale().getLanguage() == null) {
			logger.warn("Local in current context is not present, use the default {}",language);
		}else{
			
			language = commerceSession.getLocale().getLanguage();
		}
		
		return new StringReader(((TemplateStore) template).getTemplateMail().getText(language));
	}

	// Util Methods
	/**
	 * Parse the typeTemplate in id and Locale
	 * 
	 * @return leftValue = Id TypeTemplate ; rightValue = Locale value
	 */
	private CoupleStore<String, String> parseIdTypeTemplate(String idTypeTemplate) {
		CoupleStore<String, String> pathWithLocale = new CoupleStore<String, String>();

		int indexLocale = idTypeTemplate.indexOf(LOCALE_SEPARATOR);

		pathWithLocale.setLeftValue(indexLocale == -1 ? idTypeTemplate : idTypeTemplate.substring(0, indexLocale));
		pathWithLocale.setRightValue(indexLocale == -1 ? null : idTypeTemplate.substring(indexLocale + 1));

		return pathWithLocale;
	}
}
