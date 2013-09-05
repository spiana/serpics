package com.serpics.admin;

import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CatalogService;

@Component
@Scope("prototype")
public class CatPopulator {

	@Resource
	CatalogService catalogService;
	
	public void dostuff(){
		
		for (int i = 0; i < 10; i++){
			Category root = newCat();
			catalogService.createCategory(root, null);
			for (int j = 0; j< RandomUtils.nextInt(7); j++){
				Category sub1 = newCat();
				catalogService.createCategory(sub1, root);
				for (int k = 0; k< RandomUtils.nextInt(15); k++){
					Category sub2 = newCat();
					catalogService.createCategory(sub2, sub1);
				}
			}
		}
		
	}
	
	private Category newCat(){
		Category c = new Category();
		c.setCode(RandomStringUtils.randomAlphabetic(30));
		return c;
	}
}
