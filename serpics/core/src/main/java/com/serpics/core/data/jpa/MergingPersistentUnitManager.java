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
package com.serpics.core.data.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;

public class MergingPersistentUnitManager extends DefaultPersistenceUnitManager {

	Logger log = LoggerFactory.getLogger(MergingPersistentUnitManager.class);
	Map<String, List<String>> puiClasses = new HashMap<String, List<String>>();

	@Override
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {

		log.info(pui.getManagedClassNames().toString());
		// log.info(StringUtils.join(pui.getManagedClassNames(), ","));

		List<String> classes = puiClasses.get(pui.getPersistenceUnitName());
		if (classes == null) {
			classes = new ArrayList<String>();
			puiClasses.put(pui.getPersistenceUnitName(), classes);
		}
		pui.getManagedClassNames().addAll(classes);

		classes.addAll(pui.getManagedClassNames());
	}
	//
	// @Override
	// protected void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo
	// pui) {
	//
	// super.postProcessPersistenceUnitInfo(pui);
	// final URL persistenceUnitRootUrl = pui.getPersistenceUnitRootUrl();
	// pui.addJarFileUrl(persistenceUnitRootUrl);
	// final String persistenceUnitName = pui.getPersistenceUnitName();
	// final MutablePersistenceUnitInfo oldPU =
	// getPersistenceUnitInfo(persistenceUnitName);
	//
	// if (oldPU != null) {
	// List<URL> urls = oldPU.getJarFileUrls();
	// for (URL url : urls)
	// pui.addJarFileUrl(url);
	// List<String> managedClassNames = oldPU.getManagedClassNames();
	// for (String managedClassName : managedClassNames)
	// pui.addManagedClassName(managedClassName);
	// List<String> mappingFileNames = oldPU.getMappingFileNames();
	// for (String mappingFileName : mappingFileNames)
	// pui.addMappingFileName(mappingFileName);
	// Properties oldProperties = oldPU.getProperties();
	// Properties newProperties = pui.getProperties();
	// newProperties.putAll(oldProperties);
	// pui.setProperties(newProperties);
	// }
	// /*
	// * super.postProcessPersistenceUnitInfo(pui);
	// * pui.addJarFileUrl(pui.getPersistenceUnitRootUrl());
	// *
	// * MutablePersistenceUnitInfo oldPui =
	// * getPersistenceUnitInfo(pui.getPersistenceUnitName()); if (oldPui !=
	// * null) { List<URL> urls = oldPui.getJarFileUrls(); for (URL url :
	// * urls) { pui.addJarFileUrl(url); } }
	// */
	// }
}
