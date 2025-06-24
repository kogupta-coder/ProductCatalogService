package com.productCatalog.services;

import com.productCatalog.dtos.FakeStoreProductDto;
import com.productCatalog.exceptions.ProductNotFoundException;
import com.productCatalog.models.Category;
import com.productCatalog.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProductService")
public class FakeProductService implements  ProductService {
    RestTemplate template;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    FakeProductService(RestTemplate template)
    {
        this.template=template;
    }

//First check if the Product with the input productId exists in the Redis.
@Override
public Product getSingleProduct(Long productId) throws ProductNotFoundException {
Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + productId);

        if (product != null) {
        //Product exists in Redis, return it.
        //CACHE HIT
        return product;
    }

    //CACHE MISS
    ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = template.getForEntity(
            "https://fakestoreapi.com/products/" + productId,
            FakeStoreProductDto.class);

    FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if (fakeStoreProductDto == null) {
        //Wrong product Id.
        throw new ProductNotFoundException(productId, "Product with id " + productId + " doesn't exist.");
    }

    //Convert FakeStoreProductDto into Product Object.
    product = convertFaKeStoreProductToProduct(fakeStoreProductDto);

    //Before returning the Product, store it in Redis.
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + productId, product);

        return product;

  }

//    @Override
//    public Product getSingleProduct(Long id) throws ProductNotFoundException {
//      //  throw new ProductNotFoundException("Something went wrong");
//        throw new RuntimeException("Something went wrong");
//
//    }

    @Override
    public List<Product> getAllProducts() {
        //ResponseEntity<List<FakeStoreProductDto>> resp = template.getForEntity("https://fakestoreapi.com/products/",List<FakeStoreProductDto>.class);
        ResponseEntity<FakeStoreProductDto[]> resp = template.getForEntity("https://fakestoreapi.com/products/",FakeStoreProductDto[].class);
        ArrayList<Product> list = new ArrayList<>();
        for(FakeStoreProductDto dto:resp.getBody())
        {

           list.add(convertFaKeStoreProductToProduct(dto));
        }
        return list;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNo, int pageSize) {
        return null;
    }

    public Product convertFaKeStoreProductToProduct(FakeStoreProductDto productDto)
    {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImgUrl(productDto.getImage());
        product.setCategory(category);

        return product;
    }
}
