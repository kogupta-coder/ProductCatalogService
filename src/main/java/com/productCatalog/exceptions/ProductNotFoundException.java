package com.productCatalog.exceptions;

import com.productCatalog.models.Product;

public class ProductNotFoundException extends Exception{

    private Long productId;
   public  ProductNotFoundException(Long productId,String message)
    {
        super(message);
        this.productId=productId;
    }

   public ProductNotFoundException(String message)
    {
        super(message);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
