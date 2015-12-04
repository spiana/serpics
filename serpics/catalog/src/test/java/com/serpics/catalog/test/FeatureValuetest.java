package com.serpics.catalog.test;



import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.AttributeType;
import com.serpics.catalog.data.model.Feature;




@Ignore
public class FeatureValuetest {

	@Transactional
	@Test
	public void valueTest(){
		
		Feature f = new Feature();
		f.setType(AttributeType.INTEGER);
		f.setName("number");

		
	}
	
}
