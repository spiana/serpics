package com.serpics.catalog.hooks;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.core.hook.AbstractHookFactory;

@Component("inventoryHook")
@Scope("store")
public class InventoryHookFactory extends AbstractHookFactory<InventoryHook> {

	@Override
	public InventoryHook createHookInstance() {
		return new InventoryHookImpl();
	}

	@Override
	public Class<?> getObjectType() {
		return InventoryHook.class;
	}

}
