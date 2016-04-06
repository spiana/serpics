package com.serpics.membership.facade;

import com.serpics.membership.facade.data.UserData;

public interface EmailFacade {

	void sendEmailRegister(UserData userData);

}
