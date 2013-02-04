package com.serpics.commerce.hooks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.persistence.dao.OrderFactory;
import com.serpics.core.hook.AbstractHook;
import com.serpics.core.security.UserPrincipal;
import com.serpics.membership.persistence.User;

public class CommerceHookImpl extends AbstractHook implements CommerceHook {

	@Autowired(required=true)
	OrderFactory orderFactory;
	
	public void setOrderFactory(OrderFactory orderFactory) {
		this.orderFactory = orderFactory;
	}

	@Override
	public void mergeCart(User oldUser) {
		UserPrincipal u =  getSessionContext().getUserPrincipal();
	
		List<Orderitem> items = this.orderFactory.fetchCurrentOrderItem(oldUser.getUserId(), oldUser.getUserId(), getSessionContext().getStoreId());
		for (Orderitem orderitem : items) {
			orderitem.setUserId(u.getUserId());
			orderitem.setCustomerId(u.getUserId());
			orderFactory.merge(orderitem);
		}
		
	}

}
