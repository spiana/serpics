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
package com.serpics.base.test;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.i18n.data.model.Locale;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ 
	 "classpath:META-INF/i18n-serpics.xml",
	"classpath:META-INF/mediasupport-serpics.xml",
	"classpath:META-INF/base-serpics.xml"})
@SerpicsTest("default-store")
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTest extends AbstractTransactionalJunit4SerpicTest{
	
 @Test
 public void test(){
	RepositoryInitializer r =  RepositoryInitializer.getInstance();
	Repository<?, ?> rep = r.getRepositoryForEntity(Locale.class);
	Assert.assertNotNull(rep);
 }

}
