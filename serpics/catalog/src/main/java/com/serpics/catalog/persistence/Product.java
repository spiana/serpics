package com.serpics.catalog.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@PrimaryKeyJoinColumn(name = "product_Id")
@DiscriminatorValue("0")
public class Product extends AbstractProduct {

}
