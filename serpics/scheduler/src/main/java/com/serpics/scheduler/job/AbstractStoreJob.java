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
package com.serpics.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;

/**
 * An abstract implementation of Job for Ecommerce platform. 
 * Job run the execute method after a connect to store defined and a catalog. If catalog is not defined, the platform select to default.
 * 
 * After run execute method, job run disconnect method.
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
public abstract class AbstractStoreJob extends AbstractJob {

	private String realmStore;
	
	private String catalog;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CommerceEngine commerceEngine;
	
	protected abstract void executeJob(JobExecutionContext jobcontext, CommerceSessionContext commerceContext)
			throws JobExecutionException;
	
	@Override
	protected void executeJob(JobExecutionContext jobcontext) throws JobExecutionException {
		
		Assert.notNull(realmStore,"Store cannot be null");
		CommerceSessionContext commerceContext = connectToStore();
		try{
			executeJob(jobcontext, commerceContext);
		}catch(JobExecutionException e){
			logger.error("Errore nell'esecuzione del job",e);
			throw e;
		}finally{
		
			commerceEngine.disconnect(commerceContext);
		}
		
	}
	
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
