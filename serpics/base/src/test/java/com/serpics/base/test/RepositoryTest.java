package com.serpics.base.test;



import org.junit.Assert;
import org.junit.Test;

import com.serpics.base.data.model.Locale;
import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;

public class RepositoryTest  extends BaseTest{
	
	@Test
 public void test(){
	RepositoryInitializer r =  RepositoryInitializer.getInstance();
	Repository rep = r.getRepositoryForEntity(Locale.class);
	Assert.assertNotNull(rep);
 }

}
