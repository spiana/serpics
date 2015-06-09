package com.serpics.membership.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFacade {

	public UserData getCurrentuser();
	public Page<UserData> findAllUser(Pageable page);

}
