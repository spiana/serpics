package com.serpics.base.services;

public interface BaseService {
	public void initIstance();

	public boolean isInitialized();

	public void createStore(String storeName);
}
