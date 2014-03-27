package com.serpics.catalog.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Locale;
import com.serpics.base.services.LocaleService;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CtentryDescr;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.CtentryDescrService;
import com.serpics.core.SerpicsException;


public class CategoryServiceTest extends CatalogBaseTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    CtentryDescrService ctentryDescrService;

    @Autowired
    LocaleService localeService;



    @Test
    public void test0() throws SerpicsException {
        final Category category = new Category();
        category.setCatalog((Catalog) context.getCatalog());
        category.setCode("test0");
    }

    // @Test
    public void test2() throws SerpicsException {


        final Category example = new Category();
        example.setCode("cat1");

        final List<Category> categories = categoryService.findByexample(example);
        Assert.assertEquals(1L, categories.size());

        final Category category1 = categories.get(0);
        Assert.assertNotNull(category1);
        Assert.assertEquals(1L, category1.getCtentryDescrs().size());
    }

    @Test
    public void test1() throws SerpicsException {

        final Locale locale = localeService.findByLanguage("it");
        final Catalog catalog = (Catalog) context.getCatalog();

        Category category = new Category();
        category.setCatalog(catalog);
        category.setCode("cat1");
        // category.setUrl("cate1");
        category = categoryService.create(category);

        final CtentryDescr descr = new CtentryDescr();
        descr.setLocale(locale);
        descr.setCtentry(category);
        descr.setName("test");

        ctentryDescrService.create(descr);

        categoryService.detach(category);

        final List<Category> categories = categoryService.findAll();
        Assert.assertEquals(1L, categories.size());

        Assert.assertNotNull(locale);


        ctentryDescrService.create(descr);
        final List<CtentryDescr> descrs = ctentryDescrService.findAll();
        Assert.assertEquals(1L, descrs.size());

        final CtentryDescr c = new CtentryDescr();
        c.setCtentry(categories.get(0));
        // c.setLocale(locale);

        final List<CtentryDescr> descrs2 = ctentryDescrService.findByexample(c);
        Assert.assertEquals(1L, descrs2.size());

        final Category example = new Category();
        example.setCode("cat1");

        final List<Category> categories1 = categoryService.findByexample(example);
        Assert.assertEquals(1L, categories.size());

        final Category category1 = categories1.get(0);
        Assert.assertNotNull(category1);
        Assert.assertEquals(1L, category1.getCtentryDescrs().size());

    }


}
