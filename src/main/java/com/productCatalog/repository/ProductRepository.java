package com.productCatalog.repository;

import com.productCatalog.models.Category;
import com.productCatalog.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    Optional<Product> findById(Long Id);

    //select * from products where lower(title) LIKE '%iphone%'
    Page<Product> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    //find all the products where price >= 100 and <= 1000
    List<Product> findByPriceBetween(Double priceAfter, Double priceBefore);

    //select * from products where category_id = category.id;
    List<Product> findByCategory(Category category);

    List<Product> findAllByCategory_Id(Long categoryId);

    //JOIN Query
    List<Product> findAllByCategory_Title(String categoryTitle);
}
