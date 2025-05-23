package com.productCatalog.services;

import com.productCatalog.exceptions.CategoryNotFoundException;
import com.productCatalog.exceptions.ProductNotFoundException;
import com.productCatalog.models.Category;
import com.productCatalog.models.Product;
import com.productCatalog.repository.CategoryRepository;
import com.productCatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public CategoryRepository categoryRepository;


    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
//        Optional<Product> optionalProduct = productRepository.findById(id);

      return  productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id,"Product is not FOund"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
//       Category category = product.getCategory();
//       if(category==null)
//       {
//           throw new CategoryNotFoundException("Product cant be created without category");
//       }
//       Optional<Category> optionalCategory = categoryRepository.findByTitle(category.getTitle());
//       if(optionalCategory.isEmpty())
//       {
//           category=categoryRepository.save(category);
//       }
//       else {
//           category=optionalCategory.get();
//       }
//       product.setCategory(category);
      product= productRepository.save(product);
      return product;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
