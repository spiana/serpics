package com.serpics.membership.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.MembershipService;
import com.serpics.membership.services.UserRegService;
import com.serpics.membership.services.UserService;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class,
		DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional
public class MembershipTestCase {

	@Autowired
	BaseService b;

	@Autowired
	MembershipService m;
	@Autowired
	CommerceEngine ce;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRegService userRegService;
	
	@Before
	public void beforetest(){
		b.initIstance();	
	}
	@Test
	public void test0() throws SerpicsException{
		CommerceSessionContext context = ce.connect("default-store",
				"superuser", "admin".toCharArray());
		Page p = userService.findAll(new PageRequest(0, 10));
		assertEquals(2, p.getContent().size());
		List<User> l = userService.findAll();
		assertEquals(2, l.size());
	}
	@Test
	@Transactional
	public void test() throws SerpicsException {

	
		CommerceSessionContext context = ce.connect("default-store",
				"superuser", "admin".toCharArray());
		assertNotNull("not connect with context !", context);
		assertNotNull("notstore in context !", context.getStoreRealm());
		
		context = ce.connect(context, "superuser", "admin".toCharArray());
		assertNotNull("not connect with context !", context);
		registerTestUser(context);
		context = ce
				.connect("default-store", "test1", "password".toCharArray());
		assertNotNull("not connect with context !", context);
		final User u = (User) context.getUserPrincipal();
		assertArrayEquals("verify is test user", "test1".toCharArray(), u
				.getUserReg().getLogonid().toCharArray());
		assertArrayEquals("primaryAddress nickname",
				"test-address".toCharArray(), u.getPrimaryAddress()
						.getNickname().toCharArray());
		User example = new User();
		example.setUserType(UserType.REGISTERED);
		example.setLastname("testmembership");

		List<User> l1 = m.findbyexample(example);
		assertEquals(1, l1.size());

		UsersReg r = new UsersReg();
		r.setLogonid("test1");
		List<UsersReg> l = m.findbyexample(r);
		assertEquals(1, l.size());
		
		l = userRegService.findAll(new Specification<UsersReg>() {
			
			@Override
			public Predicate toPredicate(Root<UsersReg> arg0, CriteriaQuery<?> arg1,
					CriteriaBuilder arg2) {
				// TODO Auto-generated method stub
				return arg2.equal(arg0.get("user"), u);
			}
		}, new PageRequest(0, 1));
		
		assertEquals(1, l.size());
		
		u.addAdress(new PermanentAddress());
		m.updateUser(u);

		assertEquals(1, l1.get(0).getPermanentAddresses().size());

		l1 = m.findAll();
		assertEquals(2, l1.size());
	}

	private void registerTestUser(CommerceSessionContext context) {

		User u = new User();
		u.setLastname("testmembership");
		UsersReg ur = new UsersReg();
		ur.setLogonid("test1");
		ur.setPassword("password");
		ur.setStatus(UserRegisterType.ACTIVE);
		PermanentAddress a = new PermanentAddress();
		a.setNickname("test-address");

		u = m.registerUser(u, ur, a);

	}

}
