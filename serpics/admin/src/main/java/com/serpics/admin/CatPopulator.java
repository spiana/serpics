package com.serpics.admin;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CategoryService;

@Component
@Scope("prototype")
public class CatPopulator {

    @Resource
    CategoryService catalogService;

    public void dostuff(){

        for (int i = 0; i < 10; i++){
            final Category root = newCat();
            catalogService.create(root, null);
            for (int j = 0; j< RandomUtils.nextInt(7); j++){
                final Category sub1 = newCat();
                catalogService.create(sub1, root);
                for (int k = 0; k< RandomUtils.nextInt(15); k++){
                    final Category sub2 = newCat();
                    catalogService.create(sub2, sub1);
                }
            }
        }

    }

    private Category newCat(){
        final Category c = new Category();
        c.setCode(RandomStringUtils.randomAlphabetic(30));
        return c;
    }
}
