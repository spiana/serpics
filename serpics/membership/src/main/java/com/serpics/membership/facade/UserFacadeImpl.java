package com.serpics.membership.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.annotations.Where;
import org.hibernate.dialect.function.TrimFunctionTemplate.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.CountryData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserSpecification;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("userFacade")
@Transactional(readOnly=true)
public class UserFacadeImpl implements UserFacade{

	@Autowired
	UserService userService;
	
	
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
	public UserData findUserById(Long userId) {
		User u = userService.findOne(userId);
		Assert.notNull(u);
		return userConvert.convert(u);
	}

	@Override
	public Page<UserData> findUserByName(String name, Pageable page) { 
		User u = new User();
		u.setEmail(name);
		//List<User> lu = userService.findByexample(Specifications.);
		List<User> lu = userService.findAll(UserSpecification.searchByName(name), page);
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
		
		//destination.setRegion();
		
		return destination;
	}

	
	@Override
	public void addAddress(AddressData address) {
		PermanentAddress _a = (PermanentAddress) buildAddress(address, new PermanentAddress());
		userService.addAddress(_a, userService.getCurrentCustomer());
		
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void addBillingAddress(AddressData address) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateContactAddress(AddressData address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBillingAddress(AddressData address) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
	public void updateAddress(String addressUUID, AddressData address) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(String userUUID, UserData user) {
		// TODO Auto-generated method stub
		
	}
}
