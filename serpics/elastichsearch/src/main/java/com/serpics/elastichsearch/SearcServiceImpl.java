package com.serpics.elastichsearch;

import javax.annotation.Resource;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.stereotype.StoreService;


@StoreService("searchService")
public class SearcServiceImpl {

	@Resource(name="client")
	TransportClient client ;
	
	@Resource
	ProductFacade productFacade;
	
	public void test (){
		
		ElasticsearchTemplate template = new ElasticsearchTemplate(client);
	
		Page<ProductData> products = productFacade.listProduct(new PageRequest(0, 10));
	
		for (ProductData productData : products) {
			template.putMapping("mytest", "product", productData)	;
		}
		
	}
}
