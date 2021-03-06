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
package com.serpics.initialsetup.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.CatalogService;
import com.serpics.catalog.services.ProductService;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.service.SystemSetupService;
import com.serpics.initialsetup.task.SystemSetupTask;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ 
	"classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/mediasupport-serpics.xml" ,
	"classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
		"classpath:META-INF/catalog-serpics.xml", "classpath:META-INF/warehouse-serpics.xml",
		"classpath:META-INF/importexport-serpics.xml", "classpath:META-INF/initialsetup-serpics-test.xml" })
public class SystemTaskServiceTest extends AbstractTransactionalJunit4SerpicTest {

	@Autowired
	BaseService baseService;

	@Autowired
	CommerceEngine commerceEngine;

	@Autowired
	CatalogService catalogService;

	CommerceSessionContext context;

	@Autowired
	LocaleRepository localeRepository;

	@Autowired
	private SystemSetupService systemSetupService;

	@Autowired
	private ProductService productService;
	
	List<SystemSetupTask> listOfTaskTest;

	@Before
	public void setUp() throws SerpicsException {
		if (!baseService.isInitialized()) {
			baseService.initIstance();
		}
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();

	//	((SystemSetupServiceImpl) systemSetupService).setListOfTask(getListOfTaskTest());
		commerceEngine.disconnect(context);
	}

//	@Resource(name = "listOfTasksTest")
//	public void setListOfTaskTest(List<SystemSetupTask> listOfTaskTest) {
//		this.listOfTaskTest = listOfTaskTest;
//	}
	
	@Test
	public void listTaskTestSample() throws SerpicsException {
		
		systemSetupService.doSystemSetupTasks(ImportType.SAMPLE);
		
		logger.info("Connect to store default-store" );
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
		
		AbstractProduct prodotto = productService.findByCode("P11");
		logger.info("Prodotto: esiste?"+ (prodotto!=null));
		if(prodotto!=null){
			logger.info(" prodotto.code = "+prodotto.getCode()+" ; prodotto.weight"+prodotto.getWeight());
		}
		Assert.assertEquals(11,prodotto.getWeight(),0);
		
		prodotto = productService.findByCode("P18");
		Assert.assertNotEquals(8,prodotto.getWeight(),0);
		commerceEngine.disconnect(context);
	}

	@Test
	public void ListTaskTestProject() throws SerpicsException{
		
		systemSetupService.doSystemSetupTasks(ImportType.PROJECT);
		
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
		
		Product prodotto = productService.findByCode("P21");
		
		logger.info("Prodotto: esiste?"+ (prodotto!=null));
		if(prodotto!=null){
			logger.info(" prodotto.code = "+prodotto.getCode()+" ; prodotto.weight"+prodotto.getWeight());
		}
		
		Assert.assertEquals(1,prodotto.getWeight(),0);
		
		prodotto = productService.findByCode("P28");
		Assert.assertNotEquals(28,prodotto.getWeight(),0);
		commerceEngine.disconnect(context);
	}
	
	@Test
	public void ListTaskTestProjectModuleTest() throws SerpicsException{
		
		systemSetupService.doSystemSetupTasks(ImportType.PROJECT , "test");
		
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.setDefaultCatalog("");

		Product prodotto = productService.findByCode("P10");
		Assert.assertNotNull(prodotto);
		
		Product prodotto1 = productService.findByCode("P20");
		Assert.assertNull(prodotto1);
		
		
		commerceEngine.disconnect(context);
	}
	
	public List<SystemSetupTask> getListOfTaskTest() {
		return listOfTaskTest;
	}

	

}
