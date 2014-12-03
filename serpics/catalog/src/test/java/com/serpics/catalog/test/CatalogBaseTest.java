package com.serpics.catalog.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.SerpicsEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.services.BaseService;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CatalogBaseTest {

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;

    CommerceSessionContext context;

    @Before
    public void beforeTest() throws SerpicsException {
        baseService.initIstance();
        context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
        catalogService.initialize();
    }


}
