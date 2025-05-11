package com.productCatalog.services;

import com.productCatalog.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    boolean deleteProduct(Long productId);
}
