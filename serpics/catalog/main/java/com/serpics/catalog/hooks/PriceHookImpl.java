package com.serpics.catalog.hooks;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.core.hook.AbstractHook;

@Component("priceHook")
@Scope("prototype")
public class PriceHookImpl extends AbstractHook implements PriceHook {

	@Override
	public Double resolveProductPrice(AbstractProduct product, String currency) {
		// TODO Auto-generated method stub
		return Double.valueOf(100.00);
	}

	@Override
	public Double resolveProductCost(AbstractProduct product, String currency) {
		// TODO Auto-generated method stub
		return Double.valueOf(80.50);
	}

}
