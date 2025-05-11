package com.productCatalog.services;

import com.productCatalog.dtos.FakeStoreProductDto;
import com.productCatalog.models.Category;
import com.productCatalog.models.Product;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeProductService implements  ProductService {
    RestTemplate template;
    FakeProductService(RestTemplate template)
    {
        this.template=template;
    }
    @Override
    public Product getSingleProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> resp= template.getForEntity("https://fakestoreapi.com/products/"+id,FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProduct = resp.getBody();

        return convertFaKeStoreProductToProduct(fakeStoreProduct);

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    public Product convertFaKeStoreProductToProduct(FakeStoreProductDto productDto)
    {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setCategory(category);
        return product;
    }
}
