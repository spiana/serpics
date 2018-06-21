package com.serpics.elastichsearch;

import java.io.StringReader;

import javax.annotation.Resource;

import org.elasticsearch.client.transport.TransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.importexport.services.ImportCsvService;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/mediasupport-serpics.xml" , "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml", "classpath:META-INF/importexport-serpics.xml" , "classpath:META-INF/elasticsearch-serpics.xml"})
	//"classpath:META-INF/elasticSearch-serpics.xml"})
public class Indexertest extends AbstractTransactionalJunit4SerpicTest {

	@Autowired
	BaseService baseService;
	@Autowired
	CommerceEngine commerceEngine;

    @Autowired
    LocaleRepository localeRepository;
    
    @Autowired
    CatalogService catalogService;
    
	@Resource(name = "client")
	TransportClient client;

	@Resource
	ProductFacade productFacade;

	@Resource
	ImportCsvService importCsvService;

	@Before
	public void beforeTest() throws SerpicsException {
		if (!baseService.isInitialized()) {
			baseService.initIstance();
		}
		CommerceSessionContext context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
	}

	@Test
	public void test() {

		String b = "code[unique];name{it}\np1;prodotto 1\np2;prodotto 2\np3;prodotto 3\n";

		importCsvService.importCsv(new StringReader(b), Product.class);

		ElasticsearchTemplate template = new ElasticsearchTemplate(client);

		Page<ProductData> products = productFacade.listProduct(new PageRequest(0, 10));

		for (ProductData productData : products) {
			template.putMapping("mytest", "product", productData);
		}

	}
}
