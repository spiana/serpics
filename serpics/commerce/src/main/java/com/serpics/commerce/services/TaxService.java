package com.serpics.commerce.services;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Tax;

public interface TaxService {
	
	public Tax findActiveTax(Store store);

}
