package com.serpics.admin.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.PropertiesUtils;
import com.vaadin.addon.jpacontainer.JPAContainer;


@ContextConfiguration( {"classpath*:META-INF/applicationContext-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class LoadSmcConfigTest {
	
	@Autowired
	PropertiesUtils props;
	
	@Autowired
	BaseService baseService;
	
	@Autowired 
	UserService userService;
	
	@Test
	public void test(){
		Assert.assertNotNull(props);

		String[] table = props.getTableProperty("test.entity");
		Assert.assertEquals(2, table.length);
	}

	@Test
	public void test1(){
		baseService.initIstance();
		JPAContainer<UsersReg> s = ServiceContainerFactory.make(UsersReg.class);
		
		for (int x=1 ; x< 200 ;x++){
			UsersReg u = new UsersReg();
			u.setLogonid("test"+x);
			userService.registerUser(u, new PrimaryAddress());
		}
		
	
		System.out.println("elemnenti presenti :" + s.getItemIds().size());
	}
}
