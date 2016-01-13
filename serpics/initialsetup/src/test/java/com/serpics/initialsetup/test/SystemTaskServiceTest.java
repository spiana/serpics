package com.serpics.initialsetup.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.service.SystemSetupService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration( {"classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml" ,
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/importexport-serpics.xml",
	"classpath:META-INF/initialsetup-serpics.xml"})
public class SystemTaskServiceTest  extends AbstractTransactionalJunit4SerpicTest{

	@Autowired
	private SystemSetupService systemSetupService;
	
	@Test
	public void listTaskTest(){
		Assert.assertNotNull(systemSetupService);
		systemSetupService.doSystemSetupTasks(ImportType.ALL);
	}
}
