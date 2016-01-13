package com.serpics.initialsetup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.importexport.services.ImportCsvService;
import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;
import com.serpics.initialsetup.service.SystemSetupTask;

public abstract class AbstractSystemTask implements SystemSetupTask {

	@Autowired
	private ImportCsvService importCsvService;
	
	@Autowired
	private CommerceEngine commerceEngine;
	
	@Override
	@Transactional
	public void execute(ImportType iType) {
		
		if(iType==ImportType.ALL || iType==ImportType.PROJECT){
			doExecuteProjectData();
		}
		if(iType==ImportType.ALL || iType==ImportType.SAMPLE){
			doExecuteSampleData();
		}
	}
	
	private CommerceSessionContext connectToStoreByAnnotationValues() throws SerpicsException{
		SystemSetupTaskConfig annotation = this.getClass().getAnnotation(SystemSetupTaskConfig.class);
		
		CommerceSessionContext commerceSessionContext = commerceEngine.connect(annotation.store());
		commerceSessionContext.setCatalog();
	}
	public abstract void doExecuteProjectData();
	
	public abstract void doExecuteSampleData();

}
