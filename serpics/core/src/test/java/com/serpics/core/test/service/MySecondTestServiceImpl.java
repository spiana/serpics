package com.serpics.core.test.service;

import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;

@StoreService(value="testService", stores={"store5"})
public class MySecondTestServiceImpl extends TestService1Impl implements
		MySecondTestService {

}