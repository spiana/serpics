/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.core.test.service;



import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.test.hook.TestHook;
import com.serpics.stereotype.StoreService;

@StoreService(value="testService", stores={"store4"} )
public class TestService1Impl extends TestServiceImpl1 implements MyTestService{
	
	@Autowired
	TestHook testHook;

	
	@Override
	public String sayHello() {
		
		return "hello";
	}


	@Override
	public String sayGood() {
		
		return testHook.sayGood();
	}

	
}
