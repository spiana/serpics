package com.serpics.catalog.test;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@Ignore
public abstract class CatalogBaseTest extends AbstractTransactionalJunit4SerpicTest {

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;
    
    CommerceSessionContext context ;
    
    @Before
    public void beforeTest() throws SerpicsException {
    	if (!baseService.isInitialized()){
    		baseService.initIstance();
    	}
    	context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		catalogService.initialize();
    }


}
