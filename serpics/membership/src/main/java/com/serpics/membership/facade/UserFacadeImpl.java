package com.serpics.membership.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.services.CountryService;
import com.serpics.base.services.RegionService;
import com.serpics.core.SerpicsException;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.MembershipException;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.membership.services.UserService;
import com.serpics.postman.service.EmailService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("userFacade")
@Transactional(readOnly = true)
public class UserFacadeImpl implements UserFacade {
	
	Logger LOG = LoggerFactory.getLogger(UserFacadeImpl.class);

	@Autowired
	AddressFacade addressFacade;
	
	@Autowired
	UserService userService;

	@Autowired
	CountryService countryService;

	@Autowired
	RegionService regionService;

	@Autowired
	BillingAddressService billingAddressService;

	@Autowired
	PermanentAddressService permanentAddressService;

	@Resource(name = "userConverter")
	AbstractPopulatingConverter<User, UserData> userConvert;

	@Autowired
	EmailFacade emailFacade;
	
	@Override
	public UserData getCurrentuser() {
		User u = userService.getCurrentCustomer();
		return userConvert.convert(u);
	}

	@Override
	public Page<UserData> findAllUser(Pageable page) {
		Page<User> users = userService.findAll(page);

		List<UserData> ulist = new ArrayList<UserData>();
		for (User user : users.getContent()) {
			ulist.add(userConvert.convert(user));
		}

		Page<UserData> udata = new PageImpl<UserData>(ulist, page, users.getTotalElements());
		return udata;
	}

	@Override
	public Page<UserData> findAllUserByUserType(UserType userType, Pageable page) {
		Page<User >users = userService.findByUserType(userType, page);

		List<UserData> ulist = new ArrayList<UserData>();
		for (User user : users.getContent()) {
			ulist.add(userConvert.convert(user));
		}

		Page<UserData> udata = new PageImpl<UserData>(ulist, page, users.getNumberOfElements());
		return udata;
	}

	@Override
	public UserData findUserById(Long userId) {
		User u = userService.findOne(userId);
		Assert.notNull(u);
		return userConvert.convert(u);
	}

	@Override
	public Page<UserData> findUserByName(final String name, final Pageable page) {
		Page<User> lu = userService.findByEmail(name, page);

		List<UserData> ulist = new ArrayList<UserData>();
		Iterator<User> i = lu.getContent().iterator();
		while (i.hasNext()) {
			ulist.add(userConvert.convert(i.next()));

		}
		Page<UserData> udata = new PageImpl<UserData>(ulist, page, lu.getNumberOfElements());
		return udata;
	}

	@Override
	@Transactional
	public void registerUser(UserData user) throws SerpicsException {

		if (userService.findByLogonid(user.getLogonid()) == null) {

			UsersReg _u = new UsersReg();
			_u.setLastname(user.getLastname());
			_u.setFirstname(user.getFirstname());

			_u.setPhone(user.getPhone());
			_u.setEmail(user.getEmail());

			_u.setLogonid(user.getLogonid());
			_u.setPassword(user.getPassword());
			_u.setChangequestion(user.getChangequestion());
			_u.setChangeanswer(user.getChangeanswer());

			_u.setUserType(user.getUserType());

			PrimaryAddress primaryAddress;

			if (user.getContactAddress() != null) {
				AddressData p = user.getContactAddress();
				primaryAddress = (PrimaryAddress) addressFacade.buildAddress(p, new PrimaryAddress());
			} else
				primaryAddress = new PrimaryAddress();

			_u = userService.registerUser(_u, primaryAddress);
			
			UserData userData = userConvert.convert(_u);
			
			emailFacade.sendEmailRegister(userData);
			
			
		} else{
			LOG.error("Invalid user Logonid inserted {}: already registered",user.getLogonid());
			
			throw new MembershipException(String.format("Invalid user Logonid inserted %s: already registered ",
					user.getLogonid()));
		}

	}

	@Override
	@Transactional
	public void updateUser(UserData user) {
		User _u = userService.getCurrentCustomer();

		UsersReg _ur = userService.findByRegUUID(_u.getUuid());
		_ur.setLastname(user.getLastname());
		_ur.setFirstname(user.getFirstname());
		_ur.setEmail(user.getEmail());
		_ur.setPhone(user.getPhone());
		_ur.setChangequestion(user.getChangequestion());
		_ur.setChangeanswer(user.getChangeanswer());

		if (user.getPassword() != null)
			_ur.setPassword(user.getPassword());

		userService.update((User) _ur);

	}

	@Override
	@Transactional
	public void updateContactAddress(AddressData a) {
		User _u = userService.getCurrentCustomer();
		PrimaryAddress _address = _u.getPrimaryAddress();
		_address = (PrimaryAddress) addressFacade.buildAddress(a, _address);
		userService.updatePrimaryAddress(_address);
	}

	@Override
	@Transactional
	public void addBillingAddress(AddressData a) {
		Assert.notNull(a, "AddBilling address null");
		BillingAddress _address = (BillingAddress) addressFacade.buildAddress(a, new BillingAddress());
		userService.addBillingAddress(_address, userService.getCurrentCustomer());
	}

	@Override
	@Transactional
	public void updateBillingAddress(AddressData a) {
		User _u = userService.getCurrentCustomer();
		BillingAddress _address = _u.getBillingAddress();

		if (_address == null) {
			_address = new BillingAddress();
			_address = userService.addBillingAddress(_address, _u);
		}
		_address = (BillingAddress) addressFacade.buildAddress(a, _address);
		userService.updateBillingAddress(_address);

	}

	@Override
	@Transactional
	public void deleteBillingAddress() {
		User _u = userService.getCurrentCustomer();
		userService.deleteBillingAddress(_u);
	}

	@Override
	@Transactional
	public void addDestinationAddress(AddressData a) {
		PermanentAddress _address = (PermanentAddress) addressFacade.buildAddress(a, new PermanentAddress());
		userService.addPermanentAddress(_address, userService.getCurrentCustomer());
	}

	@Override
	@Transactional
	public void updateDestinationAddress(AddressData a, Long id) {
		// User _u = userService.getCurrentCustomer();
		PermanentAddress _address = permanentAddressService.findOne(id);
		_address = (PermanentAddress) addressFacade.buildAddress(a, _address);
		permanentAddressService.update(_address);

	}

	@Override
	@Transactional
	public void deleteDestinationAddress(Long id) {
		User _u = userService.getCurrentCustomer();
		PermanentAddress _address = permanentAddressService.findOne(id);
		userService.deletePermanentAddress(_u, _address);
	}
}
