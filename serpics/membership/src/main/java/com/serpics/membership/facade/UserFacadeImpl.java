package com.serpics.membership.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import com.serpics.base.data.model.Country;
import com.serpics.base.facade.data.CountryData;
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
import com.serpics.membership.services.AddressService;
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.membership.services.PrimaryAddressService;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("userFacade")
@Transactional(readOnly=true)
public class UserFacadeImpl implements UserFacade{

	@Autowired
	UserService userService;
	
	@Resource
	PrimaryAddressService primaryAddressService;
	
	@Resource
	BillingAddressService billingAddressService;
	
	@Resource
	PermanentAddressService permanentAddressService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	RegionService regionService;
	
	@Resource(name="userConverter")
	AbstractPopulatingConverter<User, UserData> userConvert;
	
	@Resource(name="countryConverter")
	AbstractPopulatingConverter<Country, CountryData> countryConvert;
	
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
	public UserData findUserById(Long userId) {
		User u = userService.findOne(userId);
		Assert.notNull(u);
		return userConvert.convert(u);
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
	public Page<UserData> findUserByName(String name, Pageable page) { 
		//List<User> lu = userService.findAll(UserSpecification.searchByName(name, "name"), page);
		//return returnListUserData(lu, page);
		return null;
	}

	
	@Override
	public Page<UserData>  findUserByLogonid(final String name, Pageable page) {  
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
				
				
				
			//	UserSpecification.searchByName(name, "logonid"), page);
		return returnListUserData(lu, page);
	}
	
	public Page<UserData>  findUserByLogonidTest(final String name, Pageable page) {  
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
				
				
				
			//	UserSpecification.searchByName(name, "logonid"), page);
		return returnListUserData(lu, page);
	}
	
	private Page<UserData> returnListUserData(List<User> lu, Pageable page) {
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
		_u.setEmail(user.getEmail());
		_u.setLogonid(user.getLogonid());
		_u.setPassword(user.getPassword());
		_u.setUserType(user.getUserType());
		PrimaryAddress primaryAddress;
		
		if (user.getContactAddress()!= null){
			AddressData p = user.getContactAddress();
			primaryAddress = (PrimaryAddress) buildAddress(p, new PrimaryAddress());
			//userService.addAddress(address, member)
		}else
			primaryAddress = new PrimaryAddress();
		
		_u = userService.registerUser(_u, primaryAddress);
		
		if (user.getBillingAddress() != null){
			AddressData b = user.getBillingAddress();
			BillingAddress billingAddress = (BillingAddress) buildAddress(b, new BillingAddress());
			userService.addBillingAddress(billingAddress, _u);
		}
		if (user.getDestinationAddress() != null){
			for (AddressData address : user.getDestinationAddress()) {
				PermanentAddress _a = (PermanentAddress) buildAddress(address, new PermanentAddress());
				userService.addAddress(_a, _u);		
			}
		}
		
	}
	
	
	private AbstractAddress buildAddress(AddressData source ,  AbstractAddress destination){
		destination.setFirstname(source.getFirstname());
		destination.setLastname(source.getLastname());
		destination.setAddress1(source.getAddress1());
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
	public void addAddress(AddressData address) {
		PermanentAddress _a = (PermanentAddress) buildAddress(address, new PermanentAddress());
		userService.addAddress(_a, userService.getCurrentCustomer());
		// TODO Auto-generated method stub
		
	}
	

	@Override
	@Transactional
	public void addBillingAddress(AddressData address) {
		BillingAddress _a = (BillingAddress) buildAddress(address, new BillingAddress());
		userService.addBillingAddress(_a, userService.getCurrentCustomer());
		// TODO Auto-generated method stub
	}


	@Override
	@Transactional
	public void updateContactAddress(AddressData a) {
		// TODO Auto-generated method stub
		User _u = userService.getCurrentUser();
		PrimaryAddress _primary = _u.getPrimaryAddress();
		_primary = (PrimaryAddress) buildAddress(a, _primary);
		_u.setPrimaryAddress(_primary);
	}

	@Override
	@Transactional
	public void updateBillingAddress(AddressData a) {
		// TODO Auto-generated method stub
		//User currentUser = userService.getCurrentCustomer();
		//User _u = userService.findOne(currentUser.getId());
		User _u = userService.getCurrentCustomer();
		BillingAddress _billing = _u.getBillingAddress();
		_billing = (BillingAddress) buildAddress(a, _billing);
		_u.setBillingAddress(_billing);
		
	}

	@Override
	@Transactional
	public void updateUser(UserData user) {
			User currentUser = userService.getCurrentCustomer();
			
			User _u = userService.findOne(currentUser.getId());
			
			_u.setLastname(user.getLastname());
			_u.setFirstname(user.getFirstname());
			
			
			if (user.getContactAddress()!= null){
				AddressData p = user.getContactAddress();
				_u.setPrimaryAddress((PrimaryAddress)buildAddress(p, _u.getPrimaryAddress()));
			}
			
			if (user.getBillingAddress() != null){
				AddressData b = user.getBillingAddress();
				BillingAddress _billing = _u.getBillingAddress();
				if (_billing == null)
					_billing = new BillingAddress();
			
				_billing = (BillingAddress) buildAddress(b, _billing);
				_u.setBillingAddress(_billing);
			}
			if (user.getDestinationAddress() != null){
				for (AddressData address : user.getDestinationAddress()) {
					if (address.getUuid().isEmpty()){
						PermanentAddress _a = (PermanentAddress) buildAddress(address, new PermanentAddress());
						_u.getPermanentAddresses().add(_a);	
					}else{
						PermanentAddress _a = getPermanetByUUID(_u.getPermanentAddresses(), address.getUuid());
						Assert.notNull(_a , String.format("address not found for UUD [%s]", address.getUuid()));
						_a = (PermanentAddress) buildAddress(address, _a);
					}
				}
			}
			_u = userService.update(_u);
			userService.detach(_u);
			userService.setCurrentCustomer(_u);
	}

	private PermanentAddress getPermanetByUUID(Set<PermanentAddress> addresses , String uuid){
		Assert.notNull(uuid);
		Assert.notNull(addresses);
		PermanentAddress _a = null;
		for (PermanentAddress permanentAddress : addresses) {
			if (uuid.equals(permanentAddress.getUuid())){
				_a= permanentAddress;
				break;
			}
		}
		return _a;
	}
	
	@Override
	@Transactional
	public void updateAddress(String addressUUID, AddressData a) {
		// TODO Auto-generated method stub
		AbstractAddress _address = primaryAddressService.findByUUID(addressUUID);
		_address = (AbstractAddress) buildAddress(a, _address);
		primaryAddressService.update((PrimaryAddress)_address);
	}

	
	@Override
	@Transactional
	public void updateBillingAddress(String addressUUID, AddressData a) {
		AbstractAddress _address = billingAddressService.findByUUID(addressUUID);
		_address = (AbstractAddress) buildAddress(a, _address);
		billingAddressService.update((BillingAddress)_address);
	}
	
	@Override
	@Transactional
	public void updatePermanentAddress(String addressUUID, AddressData a) {
		AbstractAddress _address = permanentAddressService.findByUUID(addressUUID);
		_address = (AbstractAddress) buildAddress(a, _address);
		permanentAddressService.update((PermanentAddress)_address);
	}
	
	@Override
	@Transactional
	public void updateUser(String userUUID, UserData user) {
		// TODO Auto-generated method stub
		User _u = userService.findByUUID(userUUID);
		_u.setFirstname(user.getFirstname());
		_u.setLastname(user.getLastname());
		_u.setUserType(user.getUserType());
		_u.setEmail(user.getEmail());
		userService.update(_u);
		
	}
}
