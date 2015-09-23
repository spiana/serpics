package com.serpics.base.test;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.base.data.model.Locale;
import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml"})
@SerpicsTest("default-store")
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTest extends AbstractTransactionalJunit4SerpicTest{
	
 @Test
 public void test(){
	RepositoryInitializer r =  RepositoryInitializer.getInstance();
	Repository rep = r.getRepositoryForEntity(Locale.class);
	Assert.assertNotNull(rep);
 }

}
