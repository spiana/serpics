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

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value = "discountStrategy")
public class DiscountStrategyImpl implements DiscountStrategy {

    @Override
    public AbstractOrderitem applyItemDiscount(final AbstractOrderitem orderitem) {

        orderitem.setSkuNetPrice(orderitem.getSkuPrice().doubleValue() - orderitem.getDiscountAmount().doubleValue());

        if (orderitem.getDiscountPerc() > 0)
            orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
                    - orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc() / 100));
        if (orderitem.getDiscountPerc1() > 0)
            orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
                    - orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc1() / 100));
        if (orderitem.getDiscountPerc2() > 0)
            orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
                    - orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc2() / 100));

        return orderitem;
    }

    @Override
    public AbstractOrder applyOrderDiscount(final AbstractOrder order) {
        if (order.getDiscountPerc() > 0)
            order.setDiscountAmount(order.getDiscountAmount() +
                    order.getTotalProduct() *  (order.getDiscountPerc() / 100));

        return order;
    }

}
