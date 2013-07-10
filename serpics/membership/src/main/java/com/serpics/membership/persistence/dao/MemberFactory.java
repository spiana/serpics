package com.serpics.membership.persistence.dao;


import java.util.List;


import com.serpics.core.persistence.dao.BaseFactory;
import com.serpics.membership.persistence.AbstractAddress;
import com.serpics.membership.persistence.Member;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
@Deprecated
public interface MemberFactory extends BaseFactory {
	public User createUser(User user);
	public User createUser(User user , Long storeId);
	public Store createStore(Store store);
	
	public List<Member> fetchAllMember();
	
	public List<User> fetchAllUser(Long storeId);
	public List<User> fetchAllUser(Long storeId,int firstRow, int maxResult);
	public List<User>findUser(User user);
	public User fetchUser(User user);
	public User fetchUserbylogin(String login);
	public Store fetchStoreByUUID(String uuid);
	public User fetchUserByUUID(String uuid);
	
	public AbstractAddress addUserAddress(User user, PermanentAddress address);
	public AbstractAddress updateUserAddress(AbstractAddress address , boolean force);
	public List<User> findByname(String name , long storeId);

}
