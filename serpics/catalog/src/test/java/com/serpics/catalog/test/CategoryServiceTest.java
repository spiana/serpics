package com.serpics.catalog.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Locale;
import com.serpics.base.data.model.MultilingualText;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.SerpicsException;

@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CategoryServiceTest extends CatalogBaseTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    LocaleRepository localeRepository;

    
    @Test
    @Transactional
    public void test1() throws SerpicsException {

    

    	final Locale locale = localeRepository.findByLanguage("it");
        final Catalog catalog = (Catalog) context.getCatalog();
        
        int categoryInit = categoryService.findAll().size(); 

        Category category = new Category();
        category.setCatalog(catalog);
        category.setCode("cat1");
        category.setDescription(new MultilingualText(locale.getLanguage(), "descrizione"));
        category = categoryService.create(category);

        categoryService.detach(category);

        final List<Category> categories = categoryService.findAll();
        Assert.assertEquals(categoryInit + 1L, categories.size());

        Assert.assertNotNull(locale);

        Assert.assertEquals("descrizione", categories.get(categories.size() - 1).getDescription().getText(locale.getLanguage()));

    }


}
