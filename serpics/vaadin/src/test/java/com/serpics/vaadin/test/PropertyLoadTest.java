package com.serpics.vaadin.test;

import java.io.IOException;
import java.net.URL;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import com.serpics.vaadin.data.utils.PropertiesUtils;

public class PropertyLoadTest {

	
	@Test
	public void test() throws DocumentException, IOException{
		
		PropertiesUtils p = new PropertiesUtils();
		
		URL r = this.getClass().getClassLoader().getResource("test-smc.xml");
		p.loadSmcDefinition(r);
		Assert.assertEquals(15, p.getEditProperty("primaryaddress").length);
		Assert.assertEquals(6, p.getTableProperty("primaryaddress").length);
		Assert.assertEquals(2, p.getReadOnlyProperty("primaryaddress").length);
	}
	
}
