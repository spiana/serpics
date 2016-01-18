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
 * Main class that must be extended in order to run the amount of data
 * The extended class must be added the annotation {@link com.serpics.initialsetup.annotation.SystemSetupTaskConfig SystemSetupTaskConfig}
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
