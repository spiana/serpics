package com.serpics.catalog.services;

import java.io.Serializable;

import com.serpics.catalog.persistence.Product;
import com.serpics.core.service.EntityService;

public interface ProductService extends EntityService<Product, Long> , Serializable{

}
