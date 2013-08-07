package com.serpics.catalog.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// @Table(name = "products")
// @PrimaryKeyJoinColumn(name = "product_id")
@DiscriminatorValue("0")
public class Product extends AbstractProduct {

}
