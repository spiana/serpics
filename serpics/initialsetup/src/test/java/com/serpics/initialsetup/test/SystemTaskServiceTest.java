package com.serpics.initialsetup.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.CatalogService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.service.SystemSetupService;
import com.serpics.initialsetup.service.impl.SystemSetupServiceImpl;
import com.serpics.initialsetup.task.SystemSetupTask;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
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

		((SystemSetupServiceImpl) systemSetupService).setListOfTask(getListOfTaskTest());
		commerceEngine.disconnect(context);
	}

	@Resource(name = "listOfTasksTest")
	public void setListOfTaskTest(List<SystemSetupTask> listOfTaskTest) {
		this.listOfTaskTest = listOfTaskTest;
	}
	
	@Test
	public void listTaskTestSample() throws SerpicsException {
		
		systemSetupService.doSystemSetupTasks(ImportType.SAMPLE);
		
		logger.info("Connect to store default-store" );
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
		
		Product prodotto = productService.findByCode("P11");
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
	
	public List<SystemSetupTask> getListOfTaskTest() {
		return listOfTaskTest;
	}

	

}
