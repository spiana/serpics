package com.serpics.commerce.hooks;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.core.hook.AbstractHook;

public class CommerceHookImpl extends AbstractHook implements CommerceHook {

	@Override
	public void calculateTax(AbstractOrder cart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculateShipping(Orderitem orderitem) {

		// TODO Auto-generated method stub

	}

	@Override
	public void calculateShiping(AbstractOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculateProductTotal(AbstractOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculateOrderTotale(AbstractOrder order) {
		// TODO Auto-generated method stub

	}

}
