package com.serpics.commerce.hooks;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.core.hook.AbstractHook;

@Component("discountHook")
@Scope("prototype")
public class DiscountHookImpl extends AbstractHook implements DiscountHook {

	@Override
	public Orderitem applyItemDiscount(Orderitem orderitem) {

		orderitem.setSkuNetPrice(orderitem.getSkuPrice().doubleValue() - orderitem.getDiscountAmount().doubleValue());

		if (orderitem.getDiscountPerc() > 0)
			orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
					- orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc() / 100));

		return orderitem;
	}

	@Override
	public AbstractOrder applyOrderDiscount(AbstractOrder order) {
		if (order.getDiscountPerc() > 0)
			order.setDiscountAmount(order.getDiscountAmount().add(
					order.getTotalProduct().multiply(new BigDecimal(order.getDiscountPerc() / 100))));

		return order;
	}

}
