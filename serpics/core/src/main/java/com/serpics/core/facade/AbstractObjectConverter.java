package com.serpics.core.facade;

public abstract class AbstractObjectConverter<SOURCE , TARGET> {

	
	abstract public void populate(SOURCE source , TARGET target);
	abstract public TARGET createTarget();
}
