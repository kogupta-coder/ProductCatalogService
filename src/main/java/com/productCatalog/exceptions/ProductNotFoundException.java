package com.productCatalog.exceptions;

import com.productCatalog.models.Product;

public class ProductNotFoundException extends Exception{

   public ProductNotFoundException(String message)
    {
        super(message);
    }
}
