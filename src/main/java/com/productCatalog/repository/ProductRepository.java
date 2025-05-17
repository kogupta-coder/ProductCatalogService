package com.productCatalog.repository;

import com.productCatalog.models.Category;
import com.productCatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    Optional<Product> findById(Long Id);

    //select * from products where lower(title) LIKE '%iphone%'
    List<Product> findByTitleContainsIgnoreCase(String title);

    //find all the products where price >= 100 and <= 1000
    List<Product> findByPriceBetween(Double priceAfter, Double priceBefore);

    //select * from products where category_id = category.id;
    List<Product> findByCategory(Category category);

    List<Product> findAllByCategory_Id(Long categoryId);

    //JOIN Query
    List<Product> findAllByCategory_Title(String categoryTitle);
}
