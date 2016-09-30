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

import com.serpics.base.AvailableforType;
import com.serpics.base.MultiValueField;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.model.MultiValueAttribute;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.ProductVariant;
import com.serpics.catalog.data.model.VariantAttribute;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

/**
 * @author spiana
 *
 */
public class VariantEditWindow  extends EntityFormWindow<ProductVariant>{

	/**
	 * 
	 */
	public VariantEditWindow() {
		addTab(buildMainTab(), "main");
		addTab(buildPriceTab(), "prices");
		addTab(buildProductAttributeTab() , "attribute");
		addTab(buildVariantTab() , "variant");
			
	}
	
	
	private MasterForm<ProductVariant> buildMainTab(){
    	return new MasterForm<ProductVariant>(ProductVariant.class) {
            @Override
            public void init() {
                super.init();
                setDisplayProperties(new String[]{"code" ,"name","parentProduct" , "description","buyable" ,"status" ,"primaryImage", "medias" , "weight" , "weightMeas", "taxcategory","created", "updated"});
                setReadOnlyProperties(new String[] { "created", "updated" , "uuid"});
            }
            
         };
    }
	
	private  MasterDetailTable<Price, ProductVariant> buildPriceTab(){
	    	
	    	return new AbstractPriceTable<ProductVariant>() {
				private static final long serialVersionUID = 1319744867129217907L;
				};
	}
    
	private MasterDetailTable<VariantAttribute, ProductVariant> buildProductAttributeTab(){
		
		return new MasterDetailTable<VariantAttribute , ProductVariant>(VariantAttribute.class) {
    	
    		@Override
    		public void init() {
    			super.init();
    			setParentProperty("attributes");
    			setPropertyToShow(new String[] {"baseAttribute.name","value", "sequence"});
    		}
    		
    		
    		 @Override
    		public EntityFormWindow<VariantAttribute> buildEntityWindow() {
    			 EntityFormWindow<VariantAttribute> w =  new EntityFormWindow<VariantAttribute>();
    			 w.addTab(new MasterForm<VariantAttribute>(VariantAttribute.class) {
    				@Override
    				public void init() {
    					super.init();
    					setDisplayProperties(new String[]{"baseAttribute" , "sequence","value" , "localize"});
    				}
    				private void addvalueField(){
    	    			Field <?>  f = fieldGroup.getField("value");
    	    			if(f != null){
    	    				fieldGroup.unbind(f);
    	    				removeComponent(f);
    	    			}
    	    	
    	    			addComponent(super.createField("value"));
    	    				
    				}
    			
    				@Override
    				protected Field<?> createField(String pid) {
    					if (pid.equals("baseAttribute")){
    								 final JPAContainer<BaseAttribute> baseAttributeses=ServiceContainerFactory.make(BaseAttribute.class);
    								 
    								 baseAttributeses.addContainerFilter(new Compare.Equal("availablefor" ,AvailableforType.VARIANT ));
									final ComboBox combo = new ComboBox(
											"baseAttribute");
									combo.setContainerDataSource(baseAttributeses);
									combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
									combo.setItemCaptionPropertyId("name");
									combo.setFilteringMode(FilteringMode.CONTAINS);
									combo.setImmediate(true);
									combo.setConverter(new SingleSelectConverter(combo));
									fieldGroup.bind(combo, "baseAttribute");
									
									combo.addValueChangeListener(new ValueChangeListener() {
										
										@Override
										public void valueChange(ValueChangeEvent event) {
											EntityItem<BaseAttribute> attribute = baseAttributeses.getItem(event.getProperty().getValue());
											
											MultiValueField f = (MultiValueField) entityItem.getItemProperty("value").getValue();
											if (f == null){
												f = new MultiValueAttribute();
												 entityItem.getItemProperty("value").setValue(f);
											}
												
											f.setAttributeType(attribute.getEntity().getAttributeType());
											addvalueField();
											
										}
									});
									return combo;
    					}else	
    						return super.createField(pid);
    				}
    			
				}, "main");
    			 
    			 return w;
    		}
		};
	}
	
	private MasterDetailTable<ProductVariant, Product> buildVariantTab(){
		return new MasterDetailTable<ProductVariant, Product>(ProductVariant.class , "variants") {
			private static final long serialVersionUID = 4046128089106726731L;
			/* (non-Javadoc)
			 * @see com.serpics.vaadin.ui.MasterTable#init()
			 */
			@Override
			public void init() {
				super.init();
				setPropertyToShow(new String[]{"code" , "description"});
			}
			/* (non-Javadoc)
			 * @see com.serpics.vaadin.ui.MasterTable#buildEntityWindow()
			 */
			@Override
			public EntityFormWindow<ProductVariant> buildEntityWindow() {
				EntityFormWindow<ProductVariant> w = new VariantEditWindow();
				return w;
			}
		};
	}
}

