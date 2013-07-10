package com.serpics.core.restlet;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.persistence.dao.MemberFactory;
import com.serpics.membership.services.MembershipService;

public class InitResource extends AbstractServerResource {

	@Autowired(required=true)
	private MembershipService membershipService ;
	
	

	@Get("json")
	public Representation initDefaultStore(){
		return new StringRepresentation (new String("Ok"));
	}
}
