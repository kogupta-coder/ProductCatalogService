package com.productCatalog.controllers;

import com.productCatalog.ProductCatalogServiceApplication;
import com.productCatalog.exceptions.ProductNotFoundException;
import com.productCatalog.models.Product;
import com.productCatalog.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductCatalogServiceApplication.class)
class ProductControllerTest {

    @MockitoBean(name = "fakeProductService")
    ProductService service;

    @Autowired
    ProductController productController;

//    @Test
   // public void getSingleProductTest() throws ProductNotFoundException {
//        Long productId = 10l;
//        Product expectedProduct =new Product();
//        expectedProduct.setPrice(1000.0);
//        expectedProduct.setTitle("Iphone Apple");
//        expectedProduct.setDescription("Apple Iphone");
//
//        when(service.getSingleProduct(productId)).thenReturn(expectedProduct);
//
//        Product actualProduct = productController.getSingleProduct(10l).getBody();
//
//        assertEquals(expectedProduct,actualProduct);
//
//    }


    @Test
    public void getSingleProductProductNotFoundExceptionTest() throws ProductNotFoundException {
        Long productId = -1l;
        Product expectedProduct =new Product();
        expectedProduct.setPrice(1000.0);
        expectedProduct.setTitle("Iphone Apple");
        expectedProduct.setDescription("Apple Iphone");

        when(service.getSingleProduct(productId)).thenThrow(new ProductNotFoundException("Product is not Found"));

      Exception e= assertThrows(ProductNotFoundException.class,()->service.getSingleProduct(-1l));
      assertEquals("Product is not Found",e.getMessage());



    }
}