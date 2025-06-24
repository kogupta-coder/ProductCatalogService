package com.productCatalog.services;

import com.productCatalog.exceptions.CategoryNotFoundException;
import com.productCatalog.exceptions.ProductNotFoundException;
import com.productCatalog.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(Product product) throws CategoryNotFoundException;
    boolean deleteProduct(Long productId);
    Page<Product> getProductsByTitle(String title, int pageNo, int pageSize); ;
}
