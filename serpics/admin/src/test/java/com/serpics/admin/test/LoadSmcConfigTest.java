package com.serpics.admin.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.vaadin.ui.PropertiesUtils;


@ContextConfiguration( {"classpath:META-INF/appContext-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class LoadSmcConfigTest {
	
	@Autowired
	PropertiesUtils props;
	
	@Test
	public void test(){
		Assert.assertNotNull(props);

		String[] table = props.getTableProperty("test.entity");
		Assert.assertEquals(2, table.length);
	}

}
