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
package com.serpics.catalog.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.model.Locale;
import com.serpics.i18n.data.model.MultilingualText;
import com.serpics.i18n.data.repositories.LocaleRepository;

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
