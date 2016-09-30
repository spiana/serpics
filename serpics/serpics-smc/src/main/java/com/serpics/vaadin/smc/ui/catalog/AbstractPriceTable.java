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
package com.serpics.vaadin.smc.ui.catalog;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;

/**
 * @author spiana
 *
 */
public abstract class AbstractPriceTable<P extends AbstractProduct> extends MasterDetailTable<Price, P>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2334804022537606513L;

	/**
	 *
	 */
	public AbstractPriceTable() {
		super(Price.class);
	
	}

    @Override
    public EntityFormWindow<Price> buildEntityWindow() {
    	EntityFormWindow<Price> editorWindow = new EntityFormWindow<Price>();
        editorWindow.addTab(new MasterForm<Price>(Price.class) {

            @Override
            public void init() {
                super.init();
   //             setDisplayProperties(new String[] { "precedence","currentPrice", "productCost", "productPrice",
    //                    "validFrom", "validTo" });
            }

        }, "main");
    	return editorWindow;
    }
    @Override
    public void init() {
      //  setPropertyToShow(new String[] { "precedence" ,"currentPrice", "productCost", "productPrice", "currency.isoCode",
       //         "validFrom", "validTo" });
        setParentProperty("prices");
    }
}
