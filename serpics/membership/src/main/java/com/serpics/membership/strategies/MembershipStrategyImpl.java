package com.serpics.membership.strategies;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.MembershipException;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.repositories.UserRegrepository;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="membershipStrategy")
public class MembershipStrategyImpl  implements MembershipStrategy {
    @Resource
    UserRegrepository userRegRepository;

    @Override
    public UserDetail login(final Store store, final String username, final char[] password) throws SerpicsException {
        UsersReg ur = userRegRepository.findBylogonid(username);
        if (ur != null) {
            if (ur.getUserType().equals(UserType.ADMINISTRATOR) || ur.getUserType().equals(UserType.REGISTERED)
                    || ur.getUserType().equals(UserType.SUPERSUSER))
                ur = login(ur, password);
            else
                throw new MembershipException(String.format("invalid type %s for userId [%d] !", ur.getUserType(),
                        ur.getUserId()));

            // if not superuser test store
//            if (!ur.getUserType().equals(UserType.SUPERSUSER)) {
//                if (!ur.getStores().contains((Store) getCurrentContext().getRealm())) {
//                    throw new MembershipException(String.format("Invalid store relation for userId %s and store %s",
//                            ur.getLogonid(), getCurrentContext().getRealm()));
//                }
//            }
        } else {
            throw new MembershipException("no user found for loginid [" + username + "] !");
        }
        
        return ur;
    }

    protected UsersReg login(final UsersReg ur, final char[] password) throws MembershipException {

        // TODO: verificare che la password sia corretta e valida
        // TODO: password deve essere salvata in MD5 quindi la stringa
        // ricevuta deve essere convertita

        if (!ur.getStatus().equals(UserRegStatus.ACTIVE)) {
            throw new MembershipException("wrong status [" + ur.getStatus() + "] for loginid [" + ur.getLogonid()
                    + "] !");
        }
        if (ur.getPassword().equals(new String(password))) {
            ur.setLastVisit(new Date());
            ur.setLastLogin(new Timestamp(ur.getLastVisit().getTime()));
        } else {
            throw new MembershipException("wrong password for loginid [" + ur.getLogonid() + "] !");
        }

        return userRegRepository.save(ur);
    }

}
