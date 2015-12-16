

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.CategoryRelationRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.data.specification.ProductSpecification;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.importexport.services.ImportCsvService;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml" , "classpath:META-INF/importexport-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class ImportBaseTest extends AbstractTransactionalJunit4SerpicTest {

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;
    
    CommerceSessionContext context ;
    
    @Autowired
    LocaleRepository localeRepository;
    
    @Resource
    ImportCsvService importCsvService;
    
    @Resource
    ProductRepository productRepository;
    
    @Resource
    CategoryRelationRepository categoryRelationRepository;
    
    @Before
    public void beforeTest() throws SerpicsException {
    	if (!baseService.isInitialized()){
    		baseService.initIstance();
    	}
    	context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
    	context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
    }

    @Test
    public void test(){
    
		String b = "code[unique];name{it}\np1;prodotto 1\np2;prodotto 2\np3;prodotto 3\n";
		importCsvService.importCsv(new StringReader(b), Product.class);
		Assert.assertEquals(3, productRepository.findAll().size());
		String b1 = "code[unique];name{en}\np1;product 1\np5;product 5\np3;product 3\np4;product four\n";
		importCsvService.importCsv(new StringReader(b1), Product.class);
		Assert.assertEquals(5, productRepository.findAll().size());
		Assert.assertEquals("product 5", productRepository.findOne(ProductSpecification.findByName("p5")).getName().getText("en"));
		Assert.assertEquals("prodotto 1", productRepository.findOne(ProductSpecification.findByName("p1")).getName().getText("it"));
    }
    
   
    @Test
    public void test2(){
    	InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("category.csv");
    	InputStream in1 = this.getClass().getClassLoader()
                .getResourceAsStream("category-rel.csv");
    	
    	InputStream in_en = this.getClass().getClassLoader()
                .getResourceAsStream("category_en.csv");
    	
    	
    	importCsvService.importCsv(new InputStreamReader(in), Category.class);
    	importCsvService.importCsv(new InputStreamReader(in_en), Category.class);
    	
    	importCsvService.importCsv(new InputStreamReader(in1), CategoryRelation.class);
    	Assert.assertEquals(4, categoryRelationRepository.count());
    	
    	InputStream in2 = this.getClass().getClassLoader()
                .getResourceAsStream("category-rel.csv");
    	importCsvService.importCsv(new InputStreamReader(in2), CategoryRelation.class);
    	
    	Assert.assertEquals(4, categoryRelationRepository.count());
    }
}
