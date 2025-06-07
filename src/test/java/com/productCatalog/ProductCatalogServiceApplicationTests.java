package com.productCatalog;

import com.productCatalog.models.Category;
import com.productCatalog.models.Product;
import com.productCatalog.repository.CategoryRepository;
import com.productCatalog.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProductCatalogServiceApplication.class)

class ProductCatalogServiceApplicationTests {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Test
	void contextLoads() {
	}
	@Test
	@Transactional
	void testQuery() {
		//EAGER
       //Optional<Product> productOptional = productRepository.findById(1L);

		//LAZY
		Optional<Category> categoryOptional = categoryRepository.findById(1L);

        System.out.println("DEBUG");

        List<Product> products = categoryOptional.get().getProducts();

        System.out.println(products.get(0).getTitle());

		//select * from products where category_id = 2L;
//
//		System.out.println("DEBUG");
	}

}
