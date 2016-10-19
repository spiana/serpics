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
package com.serpics.catalog.facade;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Media;
import com.serpics.base.data.model.TaxCategory;
import com.serpics.base.facade.data.MediaData;
import com.serpics.base.facade.data.TaxCategoryData;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.facade.data.AbstractProductData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.services.PriceService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class AbstractProductPopulator implements Populator<AbstractProduct, AbstractProductData> {
	
	private AbstractPopulatingConverter<Price, PriceData> priceConverter;
	private AbstractPopulatingConverter<Media, MediaData> mediaConverter;

	private AbstractPopulatingConverter<TaxCategory, TaxCategoryData> taxcategoryConverter;
	
	private static Logger LOG = LoggerFactory.getLogger(AbstractProductPopulator.class);
	
	@Autowired
	PriceService priceService;
	
	@Override
	public void populate(AbstractProduct source, AbstractProductData target) {
		
		target.setBuyable(source.isBuyable());
		//target.setDowloadable(source.getDownlodable());
		target.setManufacturSku(source.getManufacturerSku());
		target.setUnitMeas(source.getUnitMeas());
		target.setWeight(source.getWeight());
		target.setWeightMeas(source.getWeightMeas());
		
		
		try {
			Price price = priceService.findProductPrice(source);
			target.setPrice(priceConverter.convert(price));
		} catch (PriceNotFoundException e) {
			LOG.warn("Price not found for product {}", source.getCode());
		}
		
		
		if(source.getMedias() != null) {
			Set<MediaData> medias = new HashSet<MediaData>();
			Set<CtentryMedia> list = source.getMedias();
			for (Media media : list) {
				medias.add(mediaConverter.convert(media));
			}
			target.setMedias(medias);
		}
		
		
		
			
		if (source.getTaxcategory() != null){
			target.setTaxCategory(taxcategoryConverter.convert(source.getTaxcategory()));
		}
				
	}

		
		@Required
		public void setPriceConverter(AbstractPopulatingConverter<Price, PriceData> priceConverter) {
			this.priceConverter = priceConverter;
		}
		
		
		@Required
		public void setMediaConverter(AbstractPopulatingConverter<Media, MediaData> mediaConverter) {
			this.mediaConverter = mediaConverter;
		}
		
		
		

		public AbstractPopulatingConverter<TaxCategory, TaxCategoryData> getTaxcategoryConverter() {
			return taxcategoryConverter;
		}

		@Required
		public void setTaxcategoryConverter(
				AbstractPopulatingConverter<TaxCategory, TaxCategoryData> taxcategoryConverter) {
			this.taxcategoryConverter = taxcategoryConverter;
		}
		
}
