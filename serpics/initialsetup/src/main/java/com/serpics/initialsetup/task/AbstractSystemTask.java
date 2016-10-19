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
package com.serpics.initialsetup.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.importexport.services.ImportCsvService;
import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;

/**
 * An implementation of task as a basis for implementing various kinds of import.
 * 
 * On the implemention of this class must be added the annotation {@link com.serpics.initialsetup.annotation.SystemSetupTaskConfig SystemSetupTaskConfig}
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
public abstract class AbstractSystemTask implements SystemSetupTask {

	Logger logger = LoggerFactory.getLogger(AbstractSystemTask.class);
			
	@Autowired
	private ImportCsvService importCsvService;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CommerceEngine commerceEngine;
	
	@Override
	@Transactional
	public void execute(ImportType iType) {
		
		CommerceSessionContext commerceContext = connectToStoreByAnnotationValues();
		
		if(iType==ImportType.ALL || iType==ImportType.PROJECT){
			logger.debug("Import Project Data...");
			doExecuteProjectData();
		}
		if(iType==ImportType.ALL || iType==ImportType.SAMPLE){
			logger.debug("Import Sample Data...");
			doExecuteSampleData();
		}
		
		commerceEngine.disconnect(commerceContext);
	}
	
	private CommerceSessionContext connectToStoreByAnnotationValues(){
		SystemSetupTaskConfig annotation = this.getClass().getAnnotation(SystemSetupTaskConfig.class);
		CommerceSessionContext commerceSessionContext = null;
		try{
			logger.debug("Try to connect to Store {} with catalog {}...",annotation.store(),annotation.catalog());
			commerceSessionContext = commerceEngine.connect(annotation.store());
			catalogService.setDefaultCatalog(annotation.catalog().trim());
		}catch(SerpicsException se){
			logger.error("Error to connect store {}",annotation.store());
			throw new RuntimeException(se);
		}
		
		return commerceSessionContext;
	}
	
	public ImportCsvService getImportCsvService() {
		return importCsvService;
	}

	public abstract void doExecuteProjectData();
	
	public abstract void doExecuteSampleData();

}
