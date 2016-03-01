package com.serpics.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;

import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;

/**
 * An abstract implementation of Job for Serpics platform. 
 * Job run the execute method after a connect to store defined and a catalog. If catalog is not defined, the platform select to default.
 * 
 * After run execute method, job run disconnect method.
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
public abstract class AbstractSerpicsJob extends QuartzJobBean {

	Logger logger = LoggerFactory.getLogger(AbstractSerpicsJob.class);
			
	private String realmStore;
	
	private String catalog;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CommerceEngine commerceEngine;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Assert.notNull(realmStore,"Store cannot be null");
		CommerceSessionContext commerceContext = connectToStore();
		try{
			executeJob(arg0, commerceContext);
		}catch(JobExecutionException e){
			logger.error("Errore nell'esecuzione del job",e);
			throw e;
		}finally{
		
			commerceEngine.disconnect(commerceContext);
		}
	}
	
	/**
	 * Method to run
	 * 
	 * @param jobcontext
	 * @param commerceContext
	 */
	protected abstract void executeJob(JobExecutionContext jobcontext,CommerceSessionContext commerceContext) throws JobExecutionException;

	private CommerceSessionContext connectToStore() throws JobExecutionException{
		
		CommerceSessionContext commerceSessionContext = null;
		try{
			logger.debug("Try to connect to Store {} with catalog {}...",realmStore,catalog);
			commerceSessionContext = commerceEngine.connect(realmStore);
			catalogService.setDefaultCatalog(org.apache.commons.lang3.StringUtils.trimToEmpty(catalog));
		}catch(SerpicsException se){
			logger.error("Error to connect store {}",realmStore);
			throw new JobExecutionException("Error to connect store operation",se);
		}
		
		return commerceSessionContext;
	}
	
	public String getRealmStore() {
		return realmStore;
	}

	public void setRealmStore(String realmStore) {
		this.realmStore = realmStore;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	
}
