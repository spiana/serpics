package com.serpics.warehouse.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml" , "classpath:META-INF/warehouse-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public abstract class WarehouseBaseTest extends AbstractTransactionalJunit4SerpicTest {

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;
    
    CommerceSessionContext context ;
    
    @Autowired
    LocaleRepository localeRepository;
    
    @Before
    public void beforeTest() throws SerpicsException {
    	if (!baseService.isInitialized()){
    		baseService.initIstance();
    	}
    	context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
    	context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
    }

}