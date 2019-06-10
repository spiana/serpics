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
package com.serpics.membership.data.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.base.data.model.Store;
import com.serpics.core.data.jpa.AbstractEntity;
import com.serpics.i18n.data.model.Currency;

//@Entity
public class Currencylookup extends AbstractEntity{

	private static final long serialVersionUID = 5123294972931944960L;

	@Id
	CurrencyLookupPK id;
	
	 @ManyToOne
	@JoinColumn(name="store_id", insertable=false, updatable=false)
	Store store;
	
	@ManyToOne
	@JoinColumn(name="currency_id", insertable=false, updatable=false)
	Currency currency;

	public CurrencyLookupPK getId() {
		return id;
	}

	public void setId(CurrencyLookupPK id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
