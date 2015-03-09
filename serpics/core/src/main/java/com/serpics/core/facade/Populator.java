package com.serpics.core.facade;

public interface Populator<SOURCE , TARGET> {

	public void populate(SOURCE source , TARGET taget);
}
