package com.serpics.catalog.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Locale;
import com.serpics.base.persistence.MultilingualString;
import com.serpics.base.services.LocaleService;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.SerpicsException;


public class CategoryServiceTest extends CatalogBaseTest {


    @Autowired
    CategoryService categoryService;


    @Autowired
    LocaleService localeService;



    @Test
    public void test1() throws SerpicsException {

        final Locale locale = localeService.findByLanguage("it");
        final Catalog catalog = (Catalog) context.getCatalog();

        Category category = new Category();
        category.setCatalog(catalog);
        category.setCode("cat1");
        category.setDescription(new MultilingualString(locale.getLanguage(), "descrizione"));
        category = categoryService.create(category);

        categoryService.detach(category);

        final List<Category> categories = categoryService.findAll();
        Assert.assertEquals(1L, categories.size());

        Assert.assertNotNull(locale);

        Assert.assertEquals("descrizione", categories.get(0).getDescription().getLocalizedString(locale.getLanguage()));

    }


}