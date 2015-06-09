package com.serpics.membership.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.data.model.User;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("userFacade")
public class UserFacadeImpl implements UserFacade{

	@Autowired
	UserService userService;
	
	@Resource(name="userConverter")
	AbstractPopulatingConverter<User, UserData> userConvert;
	
	
	@Override
	public UserData getCurrentuser() {
		return userConvert.convert(userService.getCurrentUser());
	}

	@Override
	public Page<UserData> findAllUser(Pageable page) {
		Page<User> users = userService.findAll(page); 
		
		List<UserData> ulist = new ArrayList<UserData>();
		for (User user : users.getContent()) {
			ulist.add(userConvert.convert(user));
		}
		
		Page<UserData> udata= new PageImpl<UserData>(ulist);
		return udata;
	}

}
