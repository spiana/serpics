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
