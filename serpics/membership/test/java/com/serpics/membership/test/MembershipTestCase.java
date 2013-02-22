package com.serpics.membership.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.BaseService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.MembershipService;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class MembershipTestCase {

	@Autowired
	BaseService b;

	@Autowired
	MembershipService m;
	@Autowired
	CommerceEngine ce;

	@Before
	public void init() {
		b.initIstance();
	}

	@Test
	public void test() throws SerpicsException {

		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		assertNotNull("not connect with context !", context);
		context = ce.connect(context, "superuser", "admin".toCharArray());
		assertNotNull("not connect with context !", context);
		registerTestUser(context);
		context = ce.connect("default-store", "test", "password".toCharArray());
		assertNotNull("not connect with context !", context);
		User u = (User) context.getUserPrincipal();
		assertArrayEquals("verify is test user", "test".toCharArray(), u.getUserReg().getLogonid().toCharArray());
		assertArrayEquals("primaryAddress nickname", "test-address".toCharArray(), u.getPrimaryAddress().getNickname()
				.toCharArray());
		User example = new User();
		example.setUserType(UserType.REGISTERED);
		example.setLastname("test");

		List<User> l1 = m.findbyexample(example);
		assertEquals(1, l1.size());

		UsersReg r = new UsersReg();
		r.setLogonid("test");
		List<UsersReg> l = m.findbyexample(r);
		assertEquals(1, l.size());
		u.addAdress(new PermanentAddress());
		m.updateUser(u);

		assertEquals(2, l1.get(0).getPermanentAddresses().size());

		l1 = m.findAll();
		assertEquals(2, l1.size());

	}

	private void registerTestUser(CommerceSessionContext context) {

		User u = new User();
		u.setLastname("test");
		UsersReg ur = new UsersReg();
		ur.setLogonid("test");
		ur.setPassword("password");
		ur.setStatus(UserRegisterType.ACTIVE);
		PermanentAddress a = new PermanentAddress();
		a.setNickname("test-address");

		u = m.registerUser(u, ur, a);

	}

}
