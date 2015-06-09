package com.serpics.catalog.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.model.Locale;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.SerpicsException;


public class CategoryServiceTest extends CatalogBaseTest {


    @Autowired
    CategoryService categoryService;


    @Autowired
    LocaleRepository localeRepository;



    @Test
    public void test1() throws SerpicsException {

        final Locale locale = localeRepository.findByLanguage("it");
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
