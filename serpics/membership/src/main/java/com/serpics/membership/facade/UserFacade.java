package com.serpics.membership.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.membership.facade.data.UserData;

public interface UserFacade {

	public UserData getCurrentuser();
	public Page<UserData> findAllUser(Pageable page);
	public UserData findUserById(Long userId);
	public void createUser(UserData user);
	public void updateUser(Long userid , UserData user);

}
