/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.catalog.services;

import java.util.List;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;

/**
 * @author spiana
 *
 */
@StoreService("priceListService")
public class PriceListserviceImpl extends AbstractService<CommerceSessionContext> implements PriceListService{
	public static String DEFAULT_LIST_NAME = "default-list";

	@Resource
	private PriceListRepository priceListRepository;

	/* (non-Javadoc)
	 * @see com.serpics.catalog.services.PriceListService#getDefaultPriceList()
	 */
	@Override
	public Pricelist getCurrentPriceList() {
		Store store = (Store) getCurrentContext().getStoreRealm();
		List<Pricelist> l = priceListRepository.findDefaultList(store);
		assert !l.isEmpty() : "missing default price list !";
        return l.get(0);
	}

}
