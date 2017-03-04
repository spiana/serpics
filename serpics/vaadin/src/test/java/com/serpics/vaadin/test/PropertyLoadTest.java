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
package com.serpics.vaadin.test;

import java.io.IOException;
import java.net.URL;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.vaadin.data.utils.PropertiesUtils;

public class PropertyLoadTest {

	
	@Test
	public void test() throws DocumentException, IOException{
		
		PropertiesUtils p = new PropertiesUtils();
		
		URL r = this.getClass().getClassLoader().getResource("test-smc.xml");
		p.loadSmcDefinition(r);
		Assert.assertEquals(15, p.getEditProperty(PrimaryAddress.class).length);
		Assert.assertEquals(6, p.getTableProperty(PrimaryAddress.class).length);
		Assert.assertEquals(2, p.getReadOnlyProperty(PrimaryAddress.class).length);
		Assert.assertEquals(1, p.getSearchProperty(UsersReg.class).length);
	}
	
}
