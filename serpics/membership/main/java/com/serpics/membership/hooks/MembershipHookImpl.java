package com.serpics.membership.hooks;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.hook.AbstractHook;
import com.serpics.core.hook.Hook;
import com.serpics.core.security.UserPrincipal;
import com.serpics.membership.MembershipException;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.UserRegrepository;

@Hook(type = "membershipHook")
public class MembershipHookImpl extends AbstractHook implements MembershipHook {
	@Resource
	UserRegrepository userRegRepository;

	@Override
	public UserPrincipal login(final Store store, final String username, final char[] password) throws SerpicsException {
		UsersReg ur = userRegRepository.findBylogonid(username);
		if (ur != null) {
			if (ur.getUser().getUserType().equals(UserType.ADMINISTRATOR)
					|| ur.getUser().getUserType().equals(UserType.REGISTERED))
				ur = login(ur, password);
			else
				throw new MembershipException(String.format("invalid type %s for userId [%d] !", ur.getUser()
						.getUserType(), ur.getUserId()));
			/*
			 * don't test if user is connect to store
			 * 
			 * @deprecate
			 * 
			 * UserStoreRelation userStorerel = (UserStoreRelation)
			 * memberFactory.find(UserStoreRelation.class, new
			 * MemberRelationPK(store.getStoreId() , u.getUserId()) ); if
			 * (userStorerel == null) throw new
			 * MembershipException("invalid store association  for userId [" +
			 * u.getUserId() + "] !");
			 */
		} else {
			throw new MembershipException("no user found for loginid [" + username + "] !");
		}

		return ur.getUser();
	}

	protected UsersReg login(final UsersReg ur, final char[] password) throws MembershipException {

		// TODO: verificare che la password sia corretta e valida
		// TODO: password deve essere salvata in MD5 quindi la stringa
		// ricevuta deve essere convertita

		if (!ur.getStatus().equals(UserRegisterType.ACTIVE)) {
			throw new MembershipException("wrong status [" + ur.getStatus() + "] for loginid [" + ur.getLogonid()
					+ "] !");
		}
		if (ur.getPassword().equals(new String(password))) {
			ur.getUser().setLastVisit(new Date());
			ur.setLastLogin(new Timestamp(ur.getUser().getLastVisit().getTime()));
		} else {
			throw new MembershipException("wrong password for loginid [" + ur.getLogonid() + "] !");
		}

		return userRegRepository.save(ur);
	}

}
