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
package com.serpics.commerce.strategies;

import com.serpics.commerce.ShipmodeException;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;

public interface CommerceStrategy {
    public void calculateTax(AbstractOrder order);

    public void calculateShipping(AbstractOrderitem orderitem);

    public void calculateShipping(AbstractOrder order) throws ShipmodeException;

    public void calculateProductTotal(AbstractOrder order);

    public void calculateOrderTotal(AbstractOrder order);

}
