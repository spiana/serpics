package com.serpics.base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.MemberType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.StoreRepository;
import com.serpics.membership.repositories.UserRepository;
import com.serpics.membership.services.MembershipService;

@Service("baseService")
public class BaseServiceImpl extends AbstractService implements BaseService {
	@Autowired
	UserRepository memberFactory;

	@Autowired
	StoreRepository storeFactory;

	@Autowired
	MembershipService m;

	@Autowired
	CommerceEngine ce;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void initIstance() {
		Store s = new Store();
		s.setUuid("default-store");
		s.setName("default-store");
		s = m.createStore(s);
		User anonymous = new User();
		anonymous.setMemberType(MemberType.USER);
		anonymous.setLastname("Anonymous");
		memberFactory.saveAndFlush(anonymous);

		try {
			ce.connect("default-store");
			User u = new User();
			u.setLastname("Superuser");
			u.setUserType(UserType.ADMINISTRATOR);

			UsersReg ug = new UsersReg();
			ug.setUserId(u.getMemberId());
			ug.setLogonid("superuser");
			ug.setPassword("admin");
			ug.setStatus("R");
			ug.setUser(u);

			m.registerUser(u, ug, new PermanentAddress());
		} catch (SerpicsException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isInitialized() {
		Store s = storeFactory.findByuuid("default-store");
		return s == null ? false : true;
	}

}
