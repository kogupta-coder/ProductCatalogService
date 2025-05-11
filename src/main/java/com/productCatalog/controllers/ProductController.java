package com.productCatalog.controllers;

import com.productCatalog.models.Product;
import com.productCatalog.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService service;

    ProductController(ProductService service)
    {
        this.service=service;
    }
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") long id)
    {
      return service.getSingleProduct(id);
    }
    @GetMapping("/")
    public List<Product> getAllProducts()
    {
        return new ArrayList<>();
    }

    @PostMapping()
    public boolean createProduct(@RequestBody Product product)
    {
        return false;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id)
    {
        return null;
    }
}
