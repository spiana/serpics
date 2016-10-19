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
package com.serpics.commerce.facade;

import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.core.facade.Populator;

public class OrderPopulator extends AbstractOrderPopulator implements Populator<Order,OrderData> {

	@Override
	public void populate(Order source, OrderData target) {
		super.populate(source, target);
		
	}

}
