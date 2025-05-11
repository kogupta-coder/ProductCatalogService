package com.productCatalog.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String title;
    private Double price;
    private String description;
    private String imageUrl;
    private Category category;


}
