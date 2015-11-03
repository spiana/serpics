package com.serpics.membership.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.services.CountryService;
import com.serpics.base.services.RegionService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserSpecification;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("userFacade")
@Transactional(readOnly=true)
public class UserFacadeImpl implements UserFacade{
	
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
	
	@Resource(name="userConverter")
	AbstractPopulatingConverter<User, UserData> userConvert;
	
	@Override
	public UserData getCurrentuser() {
		User u = userService.getCurrentCustomer();
		return userConvert.convert(userService.findOne(u.getId()));
	}
	
	@Override
	public Page<UserData> findAllUser(Pageable page) {
		Page<User> users = userService.findAll(page); 
		
		List<UserData> ulist = new ArrayList<UserData>();
		for (User user : users.getContent()) {
			ulist.add(userConvert.convert(user));
		}
		
		Page<UserData> udata= new PageImpl<UserData>(ulist ,page , users.getTotalElements());
		return udata;
	}
	
	@Override
	public Page<UserData> findAllUserByUserType(UserType userType, Pageable page) {  
		List<User> users = userService.findAll(UserSpecification.findByUserType(userType), page);
	
		List<UserData> ulist = new ArrayList<UserData>();
		for (User user : users) {
			ulist.add(userConvert.convert(user));
		}
		
		Page<UserData> udata= new PageImpl<UserData>(ulist ,page , users.size());
		return udata;
	}
	
	@Override
	public UserData findUserById(Long userId) {
		User u = userService.findOne(userId);
		Assert.notNull(u); 
		return userConvert.convert(u);
	}
	
	@Override
	public Page<UserData>  findUserByName(final String name, Pageable page) {  
		List<User> lu = userService.findAll(
				new Specification<User>() {
		            @Override
		            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
		                    final CriteriaBuilder cb) {
		            	Expression<String> e = null;
		            	Predicate nameLike = null;
		            	e = root.get("email");
		            	nameLike = cb.like(e, "%" +name +"%");
		            	
		            	
		                return nameLike;
		            }
				} , page);

		List<UserData> ulist = new ArrayList<UserData>();
		Iterator<User> i = lu.iterator();
		while (i.hasNext()) {
			ulist.add(userConvert.convert(i.next()));
			
		}
		Page<UserData> udata= new PageImpl<UserData>(ulist ,page , lu.size());
		return udata;
	}
	
	
	
	@Override
	@Transactional
	public void registerUser(UserData user) {
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
		
		if (user.getContactAddress()!= null){
			AddressData p = user.getContactAddress();
			primaryAddress = (PrimaryAddress) buildAddress(p, new PrimaryAddress());
		}else
			primaryAddress = new PrimaryAddress();
		
		_u = userService.registerUser(_u, primaryAddress);
	}
	
	@Override
	@Transactional
	public void updateUser(UserData user) {
			User _u = userService.getCurrentCustomer();
			
			UsersReg _ur =  userService.findByRegUUID(_u.getUuid());
			_ur.setLastname(user.getLastname());
			_ur.setFirstname(user.getFirstname());
			_ur.setEmail(user.getEmail());
			_ur.setChangequestion(user.getChangequestion());
			_ur.setChangeanswer(user.getChangeanswer());
			
			if(user.getPassword() != null)
				_ur.setPassword(user.getPassword());
			
			userService.update((User) _ur);
			
	}
	
	private AbstractAddress buildAddress(AddressData source ,  AbstractAddress destination){
		destination.setFirstname(source.getFirstname());
		destination.setLastname(source.getLastname());
		destination.setAddress1(source.getAddress1());
		destination.setStreetNumber(source.getStreetNumber());
		destination.setCity(source.getCity());
		destination.setCompany(source.getCompany());
		destination.setZipcode(source.getZipcode());
		destination.setVatcode(source.getVatcode());
		destination.setFax(source.getFax());
		
		if((source.getRegion() != null) && (source.getRegion().getUuid() != null)) destination.setRegion(regionService.findByUUID(source.getRegion().getUuid()));
		if((source.getCountry() != null) && (source.getCountry().getUuid() != null)) destination.setCountry(countryService.findByUUID(source.getCountry().getUuid()));
		return destination;
	}
	
	
	@Override
	@Transactional
	public void updateContactAddress(AddressData a) {
		User _u = userService.getCurrentCustomer();
		PrimaryAddress _address = _u.getPrimaryAddress();
		_address = (PrimaryAddress) buildAddress(a, _address);
		userService.updatePrimaryAddress(_address);
	}
	
	@Override
	@Transactional
	public void addBillingAddress(AddressData a) {
		Assert.notNull(a,"AddBilling address null");
		BillingAddress _address = (BillingAddress) buildAddress(a, new BillingAddress());
		userService.addBillingAddress(_address, userService.getCurrentCustomer());
	}		
	
	@Override
	@Transactional
	public void updateBillingAddress(AddressData a) {
		User _u = userService.getCurrentCustomer();
		BillingAddress _address = _u.getBillingAddress();
		
		if (_address == null){
			_address = new BillingAddress();
			_address = userService.addBillingAddress(_address, _u);
		}
		_address = (BillingAddress) buildAddress(a, _address);
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
		PermanentAddress _address = (PermanentAddress) buildAddress(a, new PermanentAddress());
		userService.addPermanentAddress(_address, userService.getCurrentCustomer());
	}
	
	
	@Override
	@Transactional
	public void updateDestinationAddress(AddressData a, String uuid) {
//		User _u = userService.getCurrentCustomer();
		PermanentAddress _address = permanentAddressService.findByUUID(uuid);
		_address = (PermanentAddress) buildAddress(a, _address);
		permanentAddressService.update(_address);
		
	}
	
	@Transactional	
	public void deleteDestinationAddress(String uuid) {
		User _u = userService.getCurrentCustomer();
		PermanentAddress _address = permanentAddressService.findByUUID(uuid);
		userService.deletePermanentAddress(_u, _address);
	}
}
